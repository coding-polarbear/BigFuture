package kr.purplebeen.big_future

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by baehyeonbin on 2018. 2. 10..
 */

interface UserService {
    @GET("/users")
    fun getUserList() : Call<UserListGet>

    @GET("/users/{id}")
    fun getUser(@Path("id") id : String) : Call<UserGet>

    @POST("/users")
    @Multipart
    fun createUser(@Part("data") user : User) : Call<Success>

    @PUT("/users/{id}")
    @Multipart
    fun updateUser(@Path("id") id : String, user : User)
}

interface CapsuleService {
    @GET("/capsules")
    fun getCapsuleList() : Call<CapsuleListGet>

    @GET("/capsules/{id}")
    fun getCapsule(@Path("id") id : String) : Call<Success>
}