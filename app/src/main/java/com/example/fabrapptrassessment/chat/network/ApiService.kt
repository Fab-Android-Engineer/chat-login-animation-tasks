package com.example.fabrapptrassessment.chat.network

import com.example.fabrapptrassessment.chat.model.AllData
import com.example.fabrapptrassessment.login.LoginResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    /**
     * Returns a [List] of [ChatLogMessage] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "Chat" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("Tests/scripts/chat_log.php")
    //Call is a Retrofit method that sends a request to a webserver and returns a response
    fun getChat() : Call<AllData>

    @FormUrlEncoded
    @POST("Tests/scripts/login.php") // this send user data to endpoint
    // @Field("email"), @Field("password") matches the attribute that the end point expects
    fun sendUsersInfo(@Field("email") email : String, @Field("password") password : String) : Call<LoginResponse>

}