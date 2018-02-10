package kr.purplebeen.big_future

/**
 * Created by baehyeonbin on 2018. 2. 10..
 */

data class User(val _id : String, val userId : String, val schoolName : String, val graduationDate : String)

data class Capsule(val receiverId : String, val receiverName : String, val senderId : String, val senderName : String, val content : String) {
    var _id : String? = null
    var createdDate : String? = null
}