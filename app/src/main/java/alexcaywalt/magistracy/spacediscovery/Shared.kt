package alexcaywalt.magistracy.spacediscovery

import alexcaywalt.magistracy.spacediscovery.main_functionality.stations.chat.models.Chat
import alexcaywalt.magistracy.spacediscovery.main_functionality.stations.models.Station

class Shared {

    companion object {
        var currentStation: Station? = null
        var currentChat: Chat? = null
    }

}