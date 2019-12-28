package com.example.zip.`interface`.response

import com.example.zip.const.WebResponse
import com.example.zip.const.ZipStatus
import com.fasterxml.jackson.annotation.JsonIgnore

abstract class AbcResponse(@JsonIgnore open val zipStatus: ZipStatus) {

    fun send() = WebResponse(this, zipStatus.status)

}

class SimpleResponse(@JsonIgnore override val zipStatus: ZipStatus, val code: String, val message: String)
    : AbcResponse(zipStatus) {

    object SUCCESS {
        val OK = makeResponse(ZipStatus.ZIP_SUCCESS)
        val ACCEPTED = makeResponse(ZipStatus.ZIP_ACCEPTED)
    }

    object ERROR {
        val BAD_REQUEST = makeResponse(ZipStatus.ZIP_BAD_REQUEST)
    }
}

private fun makeResponse(zipStatus: ZipStatus) = zipStatus.run {
    SimpleResponse(this, this.code, this.message)
}