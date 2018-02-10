package kr.purplebeen.big_future

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by baehyeonbin on 2018. 2. 10..
 */

interface SignService {
    @GET("/sign")
    fun siginIn() : Call<UserSignIn>
}

interface UserService {
    @GET("/users")
    fun getUserList() : Call<UserListGet>

    @PUT("/users")
    @Multipart
    fun updateUser(@Part("data") user : User) : Call<UserGet>
}

interface CapsuleService {
    @GET("/capsules")
    fun getCapsuleList() : Call<CapsuleListGet>

    @GET("/capsules/{id}")
    fun getCapsule(@Path("id") id : String) : Call<Success>
}