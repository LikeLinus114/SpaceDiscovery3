package alexcaywalt.magistracy.spacediscovery

import alexcaywalt.magistracy.spacediscovery.main_functionality.chat.models.Chat

interface IDatabaseHandler {
    fun addChat(chat: Chat)
    fun getAllChats(): List<Chat>
    fun deleteAll()
}