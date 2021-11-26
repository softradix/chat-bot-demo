package com.example.chatbot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.databinding.BotLayoutMessagesBinding
import com.example.chatbot.databinding.UserLayoutMessagesBinding
import com.example.chatbot.model.Message
import com.example.chatbot.model.TypeWithMessage
import com.example.chatbot.utils.Constants

class MessageAdapter(var messageList: ArrayList<TypeWithMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class UserViewHolder(var bindingUser: UserLayoutMessagesBinding) :
        RecyclerView.ViewHolder(bindingUser.root) {
        fun bind(message: Message) {
            bindingUser.idTVUser.text = message.cnt
        }

    }

    class BotViewHolder(var bindingBot: BotLayoutMessagesBinding) :
        RecyclerView.ViewHolder(bindingBot.root) {
        fun bind(message: Message) {
            bindingBot.idTVBot.text = message.cnt
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constants.USER -> {
                UserViewHolder(
                    UserLayoutMessagesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            Constants.BOT -> {
                BotViewHolder(
                    BotLayoutMessagesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                throw ClassCastException("Unknown ViewType $viewType")
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> {
                holder.bind(messageList[position].message)
            }
            is BotViewHolder -> {
                holder.bind(messageList[position].message)
            }
        }

    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (messageList[position].type) {
            Constants.USER -> {
                1
            }
            else -> {
                2
            }
        }
    }
}