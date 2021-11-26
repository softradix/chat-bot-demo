package com.example.chatbot.ui.fragments.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chatbot.R
import com.example.chatbot.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var bindingWelcome: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingWelcome = FragmentWelcomeBinding.inflate(inflater, container, false)
        return bindingWelcome.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingWelcome.btGetStarted.setOnClickListener {
            this.findNavController().navigate(R.id.action_welcomeFragment_to_chatBot)
        }
    }
}