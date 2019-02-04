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

    fun addTextLabel(label : String) {
        textLabels.add(label)
    }

    fun addImageLabel(img : String) {
        imgLabels.add(img)
    }
}