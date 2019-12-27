package com.example.spacediscovery.stations

import java.time.LocalDateTime

class Message(
    var text: String,
    var sender: String,
    var dateTime: LocalDateTime
)