package alexcaywalt.magistracy.spacediscovery

import alexcaywalt.magistracy.spacediscovery.chat.models.Chat
import alexcaywalt.magistracy.spacediscovery.stations.models.Station

class Shared {

    companion object {
        var currentStation: Station? = null
        var currentChat: Chat? = null
    }

}