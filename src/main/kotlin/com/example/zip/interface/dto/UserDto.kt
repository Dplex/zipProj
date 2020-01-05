package com.example.zip.`interface`.dto

import com.example.zip.repo.model.UserVo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
open class UserDto(
    val id: Long? = null,
    val userId: String = "",
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val userPass: String = "",
    val userToken: String? = null
) {

    constructor(vo: UserVo) : this(
        id = vo.id,
        userId = vo.userLoginId,
        userPass = vo.userLoginPass,
        userToken = vo.userToken
    )

    fun toUserVo() = UserVo(
        id = id, userLoginId = userId, userLoginPass = userPass, userToken = userToken
    )

}