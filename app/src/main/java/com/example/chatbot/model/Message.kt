package com.example.chatbot.model

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("cnt")
    var cnt: String
)