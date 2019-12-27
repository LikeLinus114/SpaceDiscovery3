package com.example.spacediscovery.chat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spacediscovery.DatabaseHandler
import com.example.spacediscovery.R
import com.example.spacediscovery.Shared
import com.example.spacediscovery.stations.Message
import kotlinx.android.synthetic.main.activity_chat.*
import java.time.LocalDateTime

class ChatActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        station_image.setImageBitmap(Shared.currentStation!!.imageBitMap)

        submit.setOnClickListener {
            if (new_message.text.isNotEmpty()) {
                val db = DatabaseHandler(this)
                val chats = db.getAllChats()
                var openedChat = chats.filter {
                    !it.isClosed
                }.find {
                    it.station.name == Shared.currentStation!!.name
                }
                if (openedChat == null) {
                    openedChat = Chat(Shared.currentStation!!, arrayListOf(), false)
                    openedChat.messages.add(
                        Message(new_message.text.toString(), "me", LocalDateTime.now())
                    )
                }
                Toast.makeText(this, "The message has been sent successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "You did not enter a message", Toast.LENGTH_SHORT).show()
            }
        }
        clear.setOnClickListener {
            new_message.text.clear()
        }
    }

}