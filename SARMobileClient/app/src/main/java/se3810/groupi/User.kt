package se3810.groupi

class User() {
    var userID : Int
    var email : String
    var name : String
    var password : String
    var friendsList : ArrayList<Int>
    var createdTags : ArrayList<Tag>
    var token : String

    init {
        this.userID = -1
        this.email = ""
        this.name = ""
        this.password = ""
        this.friendsList = ArrayList<Int>()
        this.createdTags = ArrayList<Tag>()
        this.token = ""
    }

    fun addFriend(id : Int){
        if(!friendsList.contains(id)) {
            friendsList.add(id)
        }
    }

    fun removeFriend(id : Int){
        if(friendsList.contains(id)){
            friendsList.remove(id)
        }
    }

    fun createTag(){
        //TODO get coordinates of tag, and associated labels,
        //TODO add tag to createdTags and send tag information to
        //TODO database
    }

    fun removeTag(){
        //TODO remove tag from createdTags and remove
        //TODO tag from the database
    }

    fun login(email : String, password : String){
        //TODO verify credentials with database, and set user
        //TODO information if credentials are valid
    }

    fun logout(){
        //TODO remove user information and lock the system to the
        //TODO login screen.
    }
}