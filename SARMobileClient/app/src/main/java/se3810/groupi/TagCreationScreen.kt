package se3810.groupi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class TagCreationScreen : AppCompatActivity() {

    private var tag : Tag? = null
    private var location: Location? = null
    private var labelsTable: TableLayout? = null
    private var labelTextInputField: EditText? = null

    private val REQUEST_CODE_CANCEL : Int = 1
    private val REQUEST_CODE_COMPLETE : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_creation_screen)

        // Get current location of user from main activity
        val lat = this.intent.getDoubleExtra("LAT", 0.0)
        val lon = this.intent.getDoubleExtra("LON", 0.0)
        val alt = this.intent.getDoubleExtra("ALT", 0.0)

        // A tag id of 0 is used as a temporary ID.  The server will assign the tag with a new ID when it recieves
        // the request to make a new tag.
        this.location = Location(lat, lon, alt)
        this.tag = Tag(0, location!!)

        // Display fields
        val latDisplay: TextView = findViewById(R.id.tagLat) as TextView
        latDisplay.text = lat.toString()
        val lonDisplay: TextView = findViewById(R.id.tagLon) as TextView
        lonDisplay.text = lon.toString()
        val altDisplay: TextView = findViewById(R.id.tagAlt) as TextView
        altDisplay.text = alt.toString()

        // Get reference to labels table and input field for labels
        this.labelTextInputField = findViewById(R.id.labelTextInputField) as EditText
        this.labelsTable = findViewById(R.id.labelsTable) as TableLayout
    }

    fun addLabel(view: View){
        val labelRow = TableRow(this)
        val label = TextView(this)
        val text = this.labelTextInputField?.text.toString()
        this.tag?.label?.add(text)
        label.text = text
        labelRow.addView(label)
        labelsTable?.addView(labelRow)
    }

    fun cancel(view: View){
        super.onBackPressed()
    }

    fun createTag(view: View){
        val queue = Volley.newRequestQueue(this)
        var url = "http://" + getString(R.string.SERVER_IP_PORT) + "/tags?latitude=" + location?.latitude + "&longitude=" + location?.longitude

        var stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                if (response != null) {
                    System.out.println(response)
                }
            },
            Response.ErrorListener { error -> Log.e("LOG", error.toString()) })

        queue.add(stringRequest)
        super.onBackPressed()
    }
}
