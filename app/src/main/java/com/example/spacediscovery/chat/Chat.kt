package com.example.spacediscovery.chat

import com.example.spacediscovery.stations.Message
import com.example.spacediscovery.stations.Station

class Chat(var station: Station, var messages: ArrayList<Message>, var isOpen: Boolean)