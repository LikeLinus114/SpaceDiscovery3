package com.example.spacediscovery.chat

import com.example.spacediscovery.stations.Message
import com.example.spacediscovery.stations.Station
import java.time.LocalDateTime

class Chat(var station: Station, var messages: ArrayList<Message>, var isActive: Boolean) {

    fun getLastMessage(): Message {
        return messages[messages.size - 1]
    }

    fun addMessage(message: String, sender: String): String {
        if (message.isNotEmpty()) {
            this.messages.add(Message(message, sender, LocalDateTime.now().toString()))
            return "The message has been sent successfully"
        } else {
            return "You did not enter a message"
        }
    }

}