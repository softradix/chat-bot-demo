package com.example.chatbot.ui.fragments.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatbot.model.Message

class ChatBotViewModel: ViewModel() {

    val failureResponse = MutableLiveData<Throwable>()
    val answerResponse = MutableLiveData<Message>()

    fun answersResponse(map: HashMap<String, String>) {
        ChatBotRepo.receiveMessage(
            success = {
                answerResponse.value = it
            }, {
                failureResponse.value = it
            }, map
        )
    }
}