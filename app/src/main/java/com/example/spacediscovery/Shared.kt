package com.example.spacediscovery

import com.example.spacediscovery.chat.models.Chat
import com.example.spacediscovery.stations.models.Station

class Shared {

    companion object {
        var currentStation: Station? = null
        var currentChat: Chat? = null
    }

}