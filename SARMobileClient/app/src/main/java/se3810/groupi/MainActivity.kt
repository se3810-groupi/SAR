/*
 * The home screen of the SAR app.  This contains a list of nearby tags, and provides a button
 * to create a new tag at the current location of the user.
 */

package se3810.groupi

import android.content.Intent
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var nearbyTags: NearbyTags? = null
    private var locationManager: LocationManager? = null
    private var location: Location? = null
    var tagTable: TableLayout? = null
//    var client: OkHttpClient = OkHttpClient();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fetch reference to tag table
        this.tagTable = findViewById(R.id.tagTable)

        //Initialize NearbyTags
        this.nearbyTags = NearbyTags()

        //Initialize location services
        this.locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
        //TODO this hardcoded value is temporary to validate the passing of information between
        //TODO the activities for a new tag being created, and will be adjusted with the implementation
        //TODO of the location manager
        this.location = Location(-12.345678, 12.345678, 75.0)

        loadTags()
    }

    /**
     * Opens the createTag activity
     */
    fun createTag(view: View) {
        // Create an Intent to start the second activity
        val tagCreateIntent = Intent(this, TagCreationScreen::class.java)

        //Send coordinates
        tagCreateIntent.putExtra("LAT", this.location?.latitude)
        tagCreateIntent.putExtra("LON", this.location?.longitude)
        tagCreateIntent.putExtra("ALT", this.location?.altitude)

        // Start the new activity.
        startActivity(tagCreateIntent)
    }

    /**
     * Fetch tags in proximity to the user from the server, and store the results
     * locally.
     */
    fun loadTags() {
        /** TODO
         * 1) Get coordinates from device
         * 2) format tags and send request to server
         * 3) format results into a list of tags
         * 4) call nearbyTags.reload(new list of tags)
         */

        // TODO Remove this
        nearbyTags?.tags?.add(Tag(4, Location(32.1, 32.4, 52.2)))
        // TODO ^^^^^^^^^^^^

        val textView = findViewById<TextView>(R.id.text)

        val queue = Volley.newRequestQueue(this)
        val url = "http://www.google.com"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        queue.add(stringRequest)

        // Display Tags
        for (tag: Tag in nearbyTags!!.tags) {
            val id = tag.tagID
            val lat = tag.location.latitude
            val lon = tag.location.longitude
            val alt = tag.location.altitude
            val dis = 5
            println("Creating tag: $id")

            // Create new row
            val tableRow = TableRow(this)

            //Create text elements
            val idLabel = TextView(this)
            idLabel.text = id.toString()
            val idWeight =
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.1F)
            idLabel.layoutParams = idWeight


            val coordinatesWeight =
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2F)
            val latLabel = TextView(this)
            latLabel.text = lat.toString()
            latLabel.layoutParams = coordinatesWeight

            val lonLabel = TextView(this)
            lonLabel.text = lon.toString()
            lonLabel.layoutParams = coordinatesWeight

            val altLabel = TextView(this)
            altLabel.text = alt.toString()
            altLabel.layoutParams = coordinatesWeight

            val disLabel = TextView(this)
            disLabel.text = dis.toString()
            val distanceWeight =
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.3F)
            disLabel.layoutParams = distanceWeight

            //Populate row
            tableRow.addView(idLabel)
            tableRow.addView(latLabel)
            tableRow.addView(lonLabel)
            tableRow.addView(altLabel)
            tableRow.addView(disLabel)

            // Add row to table
            tagTable?.addView(tableRow)
        }
    }

//    fun getTag(url: String?) : String? {
//        var request: Request = Request.Builder().url(url).build();
//
//        var response : Response = client.newCall(request).execute()
//        return response.body()?.string();
//    }

    fun getLocation() {

    }
}
