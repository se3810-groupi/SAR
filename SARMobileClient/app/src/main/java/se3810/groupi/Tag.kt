package se3810.groupi

import android.media.Image

class Tag(id: Int, loc: Location, usr: User, paid: Boolean){
    var tagID : Int
    var location : Location
    var user : User
    var premium : Boolean
    var textLabels : ArrayList<String>
    var imgLabels: ArrayList<String>

    init {
        this.tagID = id
        this.location = loc
        this.user = usr
        this.premium = paid
        this.textLabels = ArrayList<String>()
        this.imgLabels = ArrayList<String>()
    }

    /**
     * Add a text label to be displayed with the tag
     * label: the text to store as a text label
     */
    fun addTextLabel(label : String) {
        textLabels.add(label)
    }

    /**
     * Add an image label to be displayed with the tag
     * img: the image to store as an image label
     */
    fun addImageLabel(img : String) {
        imgLabels.add(img)
    }
}