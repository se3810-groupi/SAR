package se3810.groupi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createTag(view: View){
        // Create an Intent to start the second activity
        val tagCreateIntent = Intent(this, TagCreationScreen::class.java)

        // Start the new activity.
        startActivity(tagCreateIntent)
    }
}
