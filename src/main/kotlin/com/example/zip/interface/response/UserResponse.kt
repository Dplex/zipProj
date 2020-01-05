package com.example.zip.`interface`.response

import com.example.zip.`interface`.dto.UserDto
import com.example.zip.const.ZipStatus
import com.example.zip.core.type.ResponseType
import com.fasterxml.jackson.annotation.JsonIgnore

class UserResponse(
    val users: List<UserDto>,
    @JsonIgnore val responseType: ResponseType
) : AbcResponse(responseType)

