package com.example.spacediscovery.services

import com.example.spacediscovery.stations.Message
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
                    LocalTime.of(10, 20)
                ),
                Message(
                    "bbb",
                    "Station1",
                    LocalTime.of(10, 22)
                ),
                Message(
                    "ccc",
                    "me",
                    LocalTime.of(10, 24)
                ),
                Message(
                    "ddd",
                    "Station1",
                    LocalTime.of(10, 26)
                ),
                Message(
                    "eee",
                    "Station1",
                    LocalTime.of(10, 28)
                )
            )
        }

    }

}