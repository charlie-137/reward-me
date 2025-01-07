package com.brogrammer.rewardme.utils

import com.google.gson.TypeAdapter
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTypeAdapter : TypeAdapter<Date>() {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

    override fun write(out: com.google.gson.stream.JsonWriter?, value: Date?) {
        out?.value(dateFormat.format(value))
    }

    override fun read(`in`: com.google.gson.stream.JsonReader?): Date {
        return dateFormat.parse(`in`?.nextString()) ?: Date()
    }
}
