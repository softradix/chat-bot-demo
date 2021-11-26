package com.example.chatbot.network

import com.example.chatbot.model.Message
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("get")
    fun responseAnswer(@QueryMap map: HashMap<String, String>): Call<Message>
}