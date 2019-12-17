package com.example.spacediscovery.stations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.R
import kotlinx.android.synthetic.main.activity_chat_history.*

class ChatHistoryActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_history)
        dates_list.layoutManager = LinearLayoutManager(applicationContext)
        dates_list.adapter = DatesAdapter()
    }

}