package se3810.groupi

// THIS IS NOT NEEDE AND WILL BE REMOVED
class User() {
    var userID : Int
    var email : String
    var name : String
    var password : String
    var friendsList : ArrayList<Int>
    var createdTags : HashMap<Int, Tag>
    var token : String

    init {
        this.userID = -1
        this.email = ""
        this.name = ""
        this.password = ""
        this.friendsList = ArrayList<Int>()
        this.createdTags = HashMap<Int, Tag>()
        this.token = ""
    }

    /**
     * Add a friend's userID to the list of friends.  The request process is
     * handled by the controller and server, and this method is called after
     * the friend request is accepted by the other user.
     * id: the id of the friend.
     */
    fun addFriend(id : Int){
        if(!friendsList.contains(id)) {
            friendsList.add(id)
        }
    }

    /**
     * Remove a friend's userID from the list of friends.  This is called
     * after the controller has successfully removed the friend from the server.
     * id: the id of the friend.
     */
    fun removeFriend(id : Int){
        if(friendsList.contains(id)){
            friendsList.remove(id)
        }
    }

    /**
     * Add a new tag to the set of created tags in the local cache
     * tag: the tag to add to the set of tags cretaed by the user
     */
    fun createTag(tag : Tag){
        createdTags.put(tag.tagID, tag)
    }

    /**
     * Removes an existing tag from the set of tags created by the user in the
     * local cache.
     * tagID: the id of the tag to remove
     */
    fun removeTag(tagID : Int){
        if(createdTags.containsKey(tagID)){
            createdTags.remove(tagID)
        }
    }

    fun login(email : String, password : String){
        //TODO verify credentials with database, and set user
        //TODO information if credentials are valid
    }

    /**
     * Reset the users information to the default values
     */
    fun logout(){
        this.userID = -1
        this.email = ""
        this.name = ""
        this.password = ""
        this.friendsList = ArrayList<Int>()
        this.createdTags = HashMap<Int, Tag>()
        this.token = ""
    }
}