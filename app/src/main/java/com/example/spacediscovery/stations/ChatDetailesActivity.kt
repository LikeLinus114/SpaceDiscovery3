package com.example.spacediscovery.stations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.R
import com.example.spacediscovery.chat.MessagesAdapter
import kotlinx.android.synthetic.main.activity_chat_details.*

class ChatDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_details)
        messages_list.layoutManager = LinearLayoutManager(applicationContext)
        messages_list.adapter = MessagesAdapter(arrayListOf())
    }

}