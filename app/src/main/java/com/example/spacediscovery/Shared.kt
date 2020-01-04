package com.example.spacediscovery

import com.example.spacediscovery.chat.Chat
import com.example.spacediscovery.stations.Station

class Shared {

    companion object {
        var currentStation: Station? = null
        var currentChat: Chat? = null
    }

}