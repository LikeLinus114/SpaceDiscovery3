package com.example.spacediscovery.chat

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.DatabaseHandler
import com.example.spacediscovery.R
import com.example.spacediscovery.Shared
import kotlinx.android.synthetic.main.activity_chat_list.*

class ChatListActivity: AppCompatActivity() {

    private lateinit var chatsAdapter: ChatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val db = DatabaseHandler(this)
        val chats: List<Chat>
        if (intent.getStringExtra("purpose") == "history") {
            toolbar.title = "Chat history"
            chats = db.getAllChats().filter { it.station.name == Shared.currentStation!!.name }
        } else {
            toolbar.title = "Active chats"
            chats = db.getAllChats().filter { it.isActive }
        }
        db.close()
        if (chats.isNotEmpty()) {
            no_chats_label.visibility = View.INVISIBLE
            no_chats_image.visibility = View.INVISIBLE
        } else {
            no_chats_label.visibility = View.VISIBLE
            no_chats_image.visibility = View.VISIBLE
        }
        chatsAdapter = ChatsAdapter(chats as ArrayList)
        chat_list.layoutManager = LinearLayoutManager(applicationContext)
        chat_list.adapter = chatsAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item!!.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}
