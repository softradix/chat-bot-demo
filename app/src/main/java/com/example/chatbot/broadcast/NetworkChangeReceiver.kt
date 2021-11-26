package com.example.chatbot.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.chatbot.utils.Utils


class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val status = NetworkUtil.getConnectivityStatusString(context)
        if (status == "3") {
            Utils.showInternetDialog(context)
        } else {
            Utils.hideInternetDialog()
        }
    }
}