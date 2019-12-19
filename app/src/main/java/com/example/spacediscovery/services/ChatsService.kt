package com.example.spacediscovery.services

import com.example.spacediscovery.ApplicationContext
import com.example.spacediscovery.chat.Chat

class ChatsService {

    companion object {

        val currentChats = createChats()

        private fun createChats(): List<Chat> {
            return listOf(
                /*Chat(StationService.preparedStations[0], MessagesService.messages[0]),
                Chat(StationService.preparedStations[1], MessagesService.messages[1]),
                Chat(StationService.preparedStations[2], MessagesService.messages[2])*/
            )
        }

    }

}