package se3810.groupi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class TagCreationScreen : AppCompatActivity() {

    private var tag : Tag? = null
    private var location: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_creation_screen)

        // Get current location of user from main activity
        val lat = this.intent.getDoubleExtra("LAT", 0.0)
        val lon = this.intent.getDoubleExtra("LON", 0.0)
        val alt = this.intent.getDoubleExtra("ALT", 0.0)

        println(lat)

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
    }

    fun addLabel(view: View){

    }
}
