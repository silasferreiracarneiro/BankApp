package com.example.bank.bankapp.infra.formatutils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTypeDeserializer : JsonDeserializer<Date?> {
    var formatInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, arg1: Type, arg2: JsonDeserializationContext): Date? {
        val date = element.asString
        try {
            return formatInput.parse(date)
        } catch (e: ParseException) {
            return null
        }
    }

}