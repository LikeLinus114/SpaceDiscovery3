package com.example.spacediscovery.stations

class Message(
    var text: String,
    var sender: String,
    var dateTime: String
) {
    override fun toString(): String {
        return "Message(text='$text', sender='$sender', dateTime=$dateTime)"
    }
}