package com.example.spacediscovery.services

import com.example.spacediscovery.stations.Message
import java.time.LocalDateTime
import java.time.LocalTime

class MessagesService {

    companion object {

        val messages =
            createMessages()

        private fun createMessages(): List<Message> {
            return listOf(
                Message(
                    "aaa",
                    "me",
                    LocalDateTime.now().toString()
                ),
                Message(
                    "bbb",
                    "Station1",
                    LocalDateTime.now().toString()
                ),
                Message(
                    "ccc",
                    "me",
                    LocalDateTime.now().toString()
                ),
                Message(
                    "ddd",
                    "Station1",
                    LocalDateTime.now().toString()
                ),
                Message(
                    "eee",
                    "Station1",
                    LocalDateTime.now().toString()
                )
            )
        }

    }

}