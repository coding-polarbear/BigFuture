package kr.purplebeen.big_future

import com.google.gson.annotations.SerializedName

/**
 * Created by baehyeonbin on 2018. 2. 10..
 */

class User {
    var _id : String = ""
    val userID : String = ""
    val userName : String = ""
    val picture : String = ""
    val schoolName : String = ""
    var graduationDate : String = ""

}

data class Capsule(val receiverId : String, val receiverName : String, val senderId : String, val senderName : String, val content : String, val tag : String) {
    var _id : String? = null
    var createdDate : String? = null
}

data class Status(val success : Boolean, val message : String)

// Retrofit Bean
data class Success(val status : Status)

data class UserListGet(val status : Status, @SerializedName("users")val userList : List<User>)

data class UserGet(val status : Status, val user : User)

data class CapsuleListGet(val status : Status, @SerializedName("capsules")val capsuleList : List<Capsule>)

data class CapsuleGet(val status : Status, val capsule : Capsule)

data class UserSignIn(val status : Status, val user : User ,val exists : Boolean)