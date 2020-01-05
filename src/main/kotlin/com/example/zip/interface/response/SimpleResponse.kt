package com.example.zip.`interface`.response

import com.example.zip.core.type.ResponseType
import com.fasterxml.jackson.annotation.JsonIgnore

data class SuccessResponse(@JsonIgnore val success: ResponseType.Success): AbcResponse(success)
data class ErrorResponse(@JsonIgnore val error: ResponseType.Error, @JsonIgnore val _message: String)
    : AbcResponse(error, _message)