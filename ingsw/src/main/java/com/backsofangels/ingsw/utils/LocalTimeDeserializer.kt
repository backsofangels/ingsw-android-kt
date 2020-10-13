package com.backsofangels.ingsw.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeDeserializer: JsonDeserializer<LocalTime>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalTime {
        return if (p != null) {
            LocalTime.parse(p.text, DateTimeFormatter.ofPattern("HH:mm:ss"))
        } else LocalTime.MIN
    }
}