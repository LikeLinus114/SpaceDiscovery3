package com.example.spacediscovery.chat

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.DatabaseHandler
import com.example.spacediscovery.R
import com.example.spacediscovery.Shared
import kotlinx.android.synthetic.main.activity_chat.*
import kotlin.collections.ArrayList

class ChatActivity: AppCompatActivity() {

    private lateinit var adapter: MessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        station_image.setImageBitmap(Shared.currentStation!!.imageBitMap)
        adapter = MessagesAdapter(arrayListOf())
        messages_list.layoutManager = LinearLayoutManager(applicationContext)
        messages_list.adapter = adapter

        submit.setOnClickListener {
            val result = updateMessages()
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            clear.performClick()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(new_message.windowToken, 0)
        }
        clear.setOnClickListener {
            new_message.text.clear()
        }
        updateMessages()
    }

    private fun getOpenChat(chats: ArrayList<Chat>): Chat? {
        return chats.filter {
            it.isOpen
        }.find {
            it.station.name == Shared.currentStation!!.name
        }
    }

    private fun updateMessages(): String {
        val db = DatabaseHandler(this)
        val chats = db.getAllChats() as ArrayList
        var openChat = getOpenChat(chats)
        if (openChat == null) {
            //create and add a new one if there is no such a chat
            openChat = Chat(Shared.currentStation!!.copyWithNoImage(), arrayListOf(), true)
        }
        //add the message to the chat
        val result = openChat.addMessage(new_message.text.toString(), "me")
        //update the recyclerView
        adapter.updateMessages(openChat.messages)
        if (openChat.messages.isEmpty()) {
            empty_messages_history_label.visibility = View.VISIBLE
        } else {
            chats.add(openChat)
            empty_messages_history_label.visibility = View.GONE
            messages_list.smoothScrollToPosition(openChat.messages.size - 1)
        }
        //update the DB
        db.deleteAll()
        chats.forEach {
            db.addChat(it)
        }
        db.close()
        return result
    }

}