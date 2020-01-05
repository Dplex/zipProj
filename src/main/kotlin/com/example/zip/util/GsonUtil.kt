package com.example.zip.util

import com.example.zip.core.aop.GsonIgnore
import com.google.gson.*
import org.springframework.core.io.Resource
import java.io.InputStreamReader

class GsonUtil {
    companion object {

        private val gson = GsonBuilder().addSerializationExclusionStrategy(exclusionStrategy).disableHtmlEscaping().create()
        private val excludGson = GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create()

        fun exposureFieldToJson(obj: Any) = excludGson.toJson(obj)

        fun <T> fromJson(resource: Resource, classOfT: Class<T>)
            = gson.fromJson<T>(InputStreamReader(resource.inputStream, "UTF-8"), classOfT)

        fun toJson(obj: Any) = gson.toJson(obj)

        fun getJsonObject(str: String) = JsonParser.parseString(str)

        fun <T> fromMap(map: Map<*, *>, classOfT: Class<T>) = gson.fromJson(gson.toJsonTree(map), classOfT)

    }
    object exclusionStrategy : ExclusionStrategy {
        override fun shouldSkipClass(clazz: Class<*>?): Boolean {
            return false
        }
        override fun shouldSkipField(f: FieldAttributes): Boolean {
            return f.getAnnotation(GsonIgnore::class.java) != null
        }
    }
}
