package com.example.zip.core.aop

import com.example.zip.core.type.LogTypeSerializer
import com.example.zip.core.type.ServiceConst
import com.google.gson.JsonObject
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class Transaction(
    var traceId: String? = null,
    var dt: String? = null,
    @JsonAdapter(LogTypeSerializer::class)
    @SerializedName("type")
    var logType: ServiceConst.LogType? = null,
    @SerializedName("userId")
    var _userId: String? = "Unknown",
    var thread: String? = null,
    var latency: String? = null,
    var message: JsonObject? = null,
    @Transient
    var arg1: Any? = null,
    @Transient
    var arg2: Any? = null
) {
    val userId: String
        get() = _userId ?: "AP"
}