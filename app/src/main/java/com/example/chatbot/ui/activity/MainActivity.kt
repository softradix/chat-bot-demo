package com.example.chatbot.ui.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatbot.broadcast.NetworkChangeReceiver
import com.example.chatbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mInterNetCheckReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainBinding.root.apply {
            setContentView(this)
        }
        mInterNetCheckReceiver =
            NetworkChangeReceiver()      // register check internet broadcast receiver
        @Suppress("DEPRECATION")
        registerReceiver(
            mInterNetCheckReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mInterNetCheckReceiver)
    }
}