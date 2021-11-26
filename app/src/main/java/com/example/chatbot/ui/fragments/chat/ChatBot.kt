package com.example.chatbot.ui.fragments.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.adapter.MessageAdapter
import com.example.chatbot.databinding.FragmentChatBotBinding
import com.example.chatbot.model.Message
import com.example.chatbot.model.TypeWithMessage
import com.example.chatbot.utils.Constants
import com.google.android.material.snackbar.Snackbar

class ChatBot : Fragment() {

    private lateinit var chatBotBinding: FragmentChatBotBinding
    private lateinit var dataMap: HashMap<String, String>
    private lateinit var chatBotViewModel: ChatBotViewModel
    private lateinit var messageAdapter : MessageAdapter
    private lateinit var recyclerView: RecyclerView

    private var messageList = ArrayList<TypeWithMessage>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        chatBotBinding = FragmentChatBotBinding.inflate(inflater, container, false)
        return chatBotBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getResponse()
        attachObservers()
        recyclerView = chatBotBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = messageAdapter
        }
    }

    private fun initMap(msg: String) {
        dataMap["bid"] = "161634"
        dataMap["key"] = "lhNpbWF3vfce7goo"
        dataMap["uid"] = "uid"
        dataMap["msg"] = msg
    }

    private fun attachObservers() {
        chatBotViewModel.answerResponse.observe(viewLifecycleOwner) {
            messageList.add(TypeWithMessage(Constants.BOT, Message(it.cnt)))
            messageAdapter.notifyDataSetChanged()
            recyclerView.smoothScrollToPosition(messageList.size)
            chatBotBinding.appCompatEditText.text?.clear()
            chatBotBinding.appCompatEditText.requestFocus()
        }
        chatBotViewModel.failureResponse.observe(viewLifecycleOwner) {
            Snackbar.make(chatBotBinding.root, "${it.message}", Snackbar.LENGTH_LONG).show()
        }

    }

    private fun getResponse() {

        chatBotBinding.floatingActionButton.setOnClickListener {
            val message = chatBotBinding.appCompatEditText.text?.trim().toString()
            messageList.add(TypeWithMessage(Constants.USER, Message(message)))
            messageAdapter.notifyDataSetChanged()
            chatBotBinding.appCompatEditText.text?.clear()
            chatBotBinding.appCompatEditText.requestFocus()
            recyclerView.smoothScrollToPosition(messageList.size)
            initMap(message)
            chatBotViewModel.answersResponse(map = dataMap)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataMap = HashMap()
        chatBotViewModel = ViewModelProvider(this)[ChatBotViewModel::class.java]
        messageAdapter = MessageAdapter(messageList)
    }
}