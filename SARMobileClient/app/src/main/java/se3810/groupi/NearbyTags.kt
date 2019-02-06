package se3810.groupi

class NearbyTags {
    private var tags : ArrayList<Tag>

    init {
        this.tags = ArrayList<Tag>()
    }

    /**
     * Replace the nearby tags with a new list of tags, this will be fetched
     * from the server by the controller
     * newTags: the list of new tags
     */
    fun reload(newTags : ArrayList<Tag>){
        this.tags = newTags
    }
}