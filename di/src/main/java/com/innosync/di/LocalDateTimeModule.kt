package com.innosync.di

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")


    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime {
        var dateString = json?.asString
        val dateStrings = dateString!!.split(".")
        if (dateStrings[1].length != 6) {
            dateString = dateStrings[0] + "."+ dateStrings[1] + "0".repeat(6 - dateStrings[1].length)
        }
        return LocalDateTime.parse(dateString, formatter)
    }
}