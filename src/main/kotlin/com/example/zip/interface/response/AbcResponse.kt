package com.example.zip.`interface`.response

import com.example.zip.const.WebResponse
import com.example.zip.const.ZipStatus
import com.example.zip.core.type.ResponseType
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class AbcResponse(val code: String, var message: String?) {
    @JsonIgnore
    lateinit var status: HttpStatus
    constructor(responseType: ResponseType) : this(responseType.resultCode, responseType.description) {
        this.status = responseType.httpStatus
    }
    constructor(responseType: ResponseType, message: String) : this(responseType) {
        this.message += message
    }
    fun send() = WebResponse(this, this.status)
}