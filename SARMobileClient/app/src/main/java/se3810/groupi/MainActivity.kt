/*
 * The home screen of the SAR app.  This contains a list of nearby tags, and provides a button
 * to create a new tag at the current location of the user.
 *
 * Location Services adapted from: https://github.com/googlesamples/
 * android-play-location/tree/master/BasicLocationSample/kotlin
 */

package se3810.groupi

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_INDEFINITE
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import se3810.groupi.BuildConfig.APPLICATION_ID

class MainActivity : AppCompatActivity() {

    private lateinit var nearbyTags : NearbyTags
    private lateinit var location : Location
    private lateinit var tagTable : TableLayout
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34

    // Setup Fused Location Provider entry point
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fetch reference to tag table
        this.tagTable = findViewById(R.id.tagTable)

        //Initialize NearbyTags
        this.nearbyTags = NearbyTags()

        //Initialize location services
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //Temp values that will be overwritten by getLastLocation onStart
        location = Location(-1.0,-1.0,-1.0)

        loadTags()
    }

    override fun onStart() {
        super.onStart()
        if(!checkPermissions()) {
            println("requesting permissions")
            requestPermissions()
        }else{
            println("getting location")
            getLastLocation()
        }
    }

    /**
     * Opens the createTag activity
     */
    fun createTag(view: View){
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
        nearbyTags?.tags?.add(Tag(4,Location(32.1,32.4,52.2)))
        // TODO ^^^^^^^^^^^^

        // Display Tags
        for (tag: Tag in nearbyTags!!.tags){
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
            val idWeight = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.1F)
            idLabel.layoutParams = idWeight


            val coordinatesWeight = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2F)
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
            val distanceWeight = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.3F)
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

    // Source: https://github.com/googlesamples/android-play-location/blob/master/BasicLocationSample/kotlin/
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
            if(task.isSuccessful && task.result != null){
                location.latitude = task.result!!.latitude
                location.longitude = task.result!!.longitude
                location.altitude = task.result!!.altitude

                println(location.latitude)
            }else{
                Log.w("MainActivity", "getLastLocation:exception", task.exception)
                println("No location detected")
            }
        }
    }

    // Source: https://github.com/googlesamples/android-play-location/blob/master/BasicLocationSample/kotlin/
    private fun checkPermissions() : Boolean{
        return ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED
    }

    // Source: https://github.com/googlesamples/android-play-location/blob/master/BasicLocationSample/kotlin/
    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_COARSE_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    // Source: https://github.com/googlesamples/android-play-location/blob/master/BasicLocationSample/kotlin/
    private fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_COARSE_LOCATION)) {
            // Provide an additional rationale to the user. This would happen if the user denied the
            // request previously, but didn't check the "Don't ask again" checkbox.
            Log.i("MainActivity", "Displaying permission rationale to provide additional context.")
            startLocationPermissionRequest()

        } else {
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            Log.i("MainActivity", "Requesting permission")
            startLocationPermissionRequest()
        }
    }

    // Source: https://github.com/googlesamples/android-play-location/blob/master/BasicLocationSample/kotlin/
    /**
     * Shows a [Snackbar].
     *
     * @param snackStrId The id for the string resource for the Snackbar text.
     * @param actionStrId The text of the action item.
     * @param listener The listener associated with the Snackbar action.
     */
    private fun showSnackbar(
        snackStrId: Int,
        actionStrId: Int = 0,
        listener: View.OnClickListener? = null
    ) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), getString(snackStrId),
            LENGTH_INDEFINITE)
        if (actionStrId != 0 && listener != null) {
            snackbar.setAction(getString(actionStrId), listener)
        }
        snackbar.show()
    }

    // Source: https://github.com/googlesamples/android-play-location/blob/master/BasicLocationSample/kotlin/
    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.i("MainActivity", "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                grantResults.isEmpty() -> Log.i("MainActivity", "User interaction was cancelled.")

                // Permission granted.
                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> getLastLocation()

                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                else -> {
                    showSnackbar(R.string.permission_denied_explanation, R.string.settings,
                        View.OnClickListener {
                            // Build intent that displays the App settings screen.
                            val intent = Intent().apply {
                                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                data = Uri.fromParts("package", APPLICATION_ID, null)
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            startActivity(intent)
                        })
                }
            }
        }
    }

}
