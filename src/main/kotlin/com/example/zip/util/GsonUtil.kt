package com.example.zip.util

import com.google.gson.Gson

class GsonUtil {

    companion object {
        val gson = Gson()

        fun <T> fromMap(map: Map<*, *>, classOfT: Class<T>): T {
            return gson.fromJson(gson.toJsonTree(map), classOfT)
        }
    }

}