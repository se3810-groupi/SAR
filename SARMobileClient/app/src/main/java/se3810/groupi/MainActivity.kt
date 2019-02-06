package se3810.groupi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    var nearbyTags : NearbyTags = NearbyTags()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadTags()
    }

    /**
     * Opens the createTag activity
     */
    fun createTag(view: View){
        // Create an Intent to start the second activity
        val tagCreateIntent = Intent(this, TagCreationScreen::class.java)

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
}
