package com.example.spacediscovery.chat

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.DatabaseHandler
import com.example.spacediscovery.R
import com.example.spacediscovery.Shared
import kotlinx.android.synthetic.main.activity_chat.*
import kotlin.collections.ArrayList

class ChatActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        station_image.setImageBitmap(Shared.currentStation!!.imageBitMap)
        val adapter = MessagesAdapter(arrayListOf())
        messages_list.layoutManager = LinearLayoutManager(applicationContext)
        messages_list.adapter = adapter

        submit.setOnClickListener {
            val db = DatabaseHandler(this)
            val chats = db.getAllChats() as ArrayList
            var openChat = getOpenChat(chats)
            if (openChat == null) {
                //remove image to prevent wasting the DB memory
                Shared.currentStation!!.imageBitMap = null
                //create and add a new one if there is no such a chat
                openChat = Chat(Shared.currentStation!!, arrayListOf(), true)
                chats.add(openChat)
            }
            //add the message to the chat
            val result = openChat.addMessage(new_message.text.toString(), "me")
            //update the recyclewView
            adapter.updateMessages(openChat.messages)
            if (openChat.messages.isEmpty()) {
                empty_messages_history_label.visibility = View.VISIBLE
            } else {
                empty_messages_history_label.visibility = View.GONE
            }
            //update the DB
            db.deleteAll()
            chats.forEach {
                db.addChat(it)
            }
            db.close()
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }
        clear.setOnClickListener {
            new_message.text.clear()
        }

        submit.performClick()
    }

    private fun getOpenChat(chats: ArrayList<Chat>): Chat? {
        return chats.filter {
            it.isOpen
        }.find {
            it.station.name == Shared.currentStation!!.name
        }
    }

}