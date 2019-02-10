/*
 * The home screen of the SAR app.  This contains a list of nearby tags, and provides a button
 * to create a new tag at the current location of the user.
 */

package se3810.groupi

import android.content.Intent
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    var nearbyTags : NearbyTags? = null
    private var locationManager : LocationManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize NearbyTags
        this.nearbyTags = NearbyTags()

        //Initialize location services
        this.locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?


        loadTags()
    }

    /**
     * Opens the createTag activity
     */
    fun createTag(view: View){
        // Create an Intent to start the second activity
        val tagCreateIntent = Intent(this, TagCreationScreen::class.java)

        //Send coordinates

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
    }

    fun getLocation() {

    }
}
