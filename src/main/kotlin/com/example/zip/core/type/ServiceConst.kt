package com.example.zip.core.type

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

object ServiceConst {
    sealed class LogType(val type: String) {
        object API : LogType("API")
        object SERVICE : LogType("SERVICE")
        object DB : LogType("DB")
        object MANAGER: LogType("MANAGER")
        object DYNAMO : LogType("DYNAMO")
        object ERROR : LogType("ERROR")
    }
}
class LogTypeSerializer: JsonSerializer<ServiceConst.LogType> {
    override fun serialize(src: ServiceConst.LogType, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.type)
    }
}