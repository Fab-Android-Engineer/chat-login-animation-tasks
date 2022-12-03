package com.example.fabrapptrassessment.chat.model

import com.google.gson.annotations.SerializedName

data class ChatLogMessageModel(
    @SerializedName("user_id") val userId : Int,
    @SerializedName("avatar_url")val avatarUrl : String,
    @SerializedName("name")val userName : String,
    val message : String
)

data class AllData (
    val data : List<ChatLogMessageModel>
        )


