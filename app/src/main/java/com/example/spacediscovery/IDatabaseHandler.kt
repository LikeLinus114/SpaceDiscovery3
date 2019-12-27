package com.example.spacediscovery

import com.example.spacediscovery.chat.Chat

interface IDatabaseHandler {
    fun addChat(chat: Chat)
    fun getAllChats(): List<Chat>
    fun getChatsCount(): Int
    fun deleteAll()
}