package com.example.chatbot.network

import com.example.chatbot.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRequests {

    private var retrofit: Retrofit

    init{
        retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).client(clientRequest())
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }

    private fun clientRequest(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    fun createService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}