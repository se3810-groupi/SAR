/*
 * The home screen of the SAR app.  This contains a list of nearby tags, and provides a button
 * to create a new tag at the current location of the user.
 *
 * Location Services adapted from: https://github.com/googlesamples/
 * android-play-location/tree/master/BasicLocationSample/kotlin
 */

package se3810.groupi

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.*

class MainActivity : AppCompatActivity() {

    private lateinit var nearbyTags : NearbyTags
    private lateinit var location : Location
    private lateinit var tagTable : TableLayout
    private lateinit var mLocationRequest : LocationRequest
    private val UPDATE_INTERVAL : Long = 1000 // 1 sec
    private val FASTEST_INTERVAL : Long = 500 // .5 sec
    private var locationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fetch reference to tag table
        this.tagTable = findViewById(R.id.tagTable)

        //Initialize NearbyTags
        this.nearbyTags = NearbyTags()

        //Temp values that will be overwritten by getLastLocation onStart
        location = Location(-1.0,-1.0,-1.0)

        //Get new location every 1 sec
        startLocationUpdates()

        loadTags()
    }

    /**
     * Opens the createTag activity
     */
    fun createTag(view: View) {
        // Create an Intent to start the second activity
        val tagCreateIntent = Intent(this, TagCreationScreen::class.java)

        //Send coordinates
        tagCreateIntent.putExtra("LAT", this.location.latitude)
        tagCreateIntent.putExtra("LON", this.location.longitude)
        tagCreateIntent.putExtra("ALT", this.location.altitude)

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

    //Adapted from https://github.com/codepath/android_guides/wiki/Retrieving-Location-with-LocationServices-API and
    //https://github.com/devangakhani/GPSLocation
    fun startLocationUpdates() {
        this.mLocationRequest = LocationRequest()
        this.mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        this.mLocationRequest.interval = UPDATE_INTERVAL
        this.mLocationRequest.fastestInterval = FASTEST_INTERVAL

        // Create LocationSettingRequest object
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()

        //Initialize service object
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient!!.checkLocationSettings(locationSettingsRequest)

        registerLocationListener()
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun registerLocationListener() {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                onLocationChanged(locationResult!!.lastLocation)
            }
        }
        // add permission if android version is greater then 23
        if(Build.VERSION.SDK_INT >= 23 && checkPermission()) {
            LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper())
        }
    }
    //Source: https://github.com/devangakhani/GPSLocation
    private fun onLocationChanged(location: android.location.Location) {
        // create message for toast with updated latitude and longitude
        val msg = "Updated Location: " + location.latitude  + " , " + location.longitude + " , " + location.altitude
        println(msg)

        this.location.latitude = location.latitude
        this.location.longitude = location.longitude
        this.location.altitude = location.altitude
    }

    //Source: https://github.com/devangakhani/GPSLocation
    private fun checkPermission() : Boolean {
        if (ContextCompat.checkSelfPermission(this , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            requestPermissions()
            return false
        }
    }

    //Source: https://github.com/devangakhani/GPSLocation
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf("Manifest.permission.ACCESS_FINE_LOCATION"),1)
    }

    //Source: https://github.com/devangakhani/GPSLocation
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1) {
            if (permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION ) {
                registerLocationListener()
            }
        }
    }
}
