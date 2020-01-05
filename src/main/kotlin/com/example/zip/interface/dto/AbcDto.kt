package com.example.zip.`interface`.dto

import com.example.zip.util.GsonUtil
import com.fasterxml.jackson.annotation.JsonIgnore

abstract class AbcDto(@JsonIgnore val exposeAll: Boolean = false) {
    override fun toString() = if (exposeAll.not()) GsonUtil.exposureFieldToJson(this) else GsonUtil.toJson(this)
}