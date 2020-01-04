package com.example.spacediscovery

import com.example.spacediscovery.chat.models.Chat

interface IDatabaseHandler {
    fun addChat(chat: Chat)
    fun getAllChats(): List<Chat>
    fun deleteAll()
}