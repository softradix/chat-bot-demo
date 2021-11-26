package com.example.chatbot.ui.fragments.chat


import com.example.chatbot.model.Message
import com.example.chatbot.network.ApiRequests
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object ChatBotRepo {

    private val apiService = ApiRequests.createService()

    fun receiveMessage(
        success:(Message) -> Unit,
        fail : (Throwable) -> Unit, map: HashMap<String, String>
    ){
        apiService.responseAnswer(map).enqueue(object : Callback<Message> {
            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                if(response.isSuccessful){
                    response.body()?.let { success(it) }
                }
            }

            override fun onFailure(call: Call<Message>, t: Throwable) {
                fail(t)
            }

        })
    }
}