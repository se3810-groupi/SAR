package se3810.groupi

class Tag(id: Int, loc: Location){
    var id : Int
    var location : Location
    var label : ArrayList<String>
    var image: ArrayList<String>
    var location_id : Int
    var users_id : Int

    init {
        this.id = id
        this.location = loc
        this.label = ArrayList<String>()
        this.image = ArrayList<String>()
        this.location_id = 1
        this.users_id = 1;
    }

    /**
     * Add a text label to be displayed with the tag
     * label: the text to store as a text label
     */
    fun addTextLabel(newLabel : String) {
        label.add(newLabel)
    }

    /**
     * Add an image label to be displayed with the tag
     * img: the image to store as an image label
     */
    fun addImageLabel(img : String) {
        image.add(img)
    }
}