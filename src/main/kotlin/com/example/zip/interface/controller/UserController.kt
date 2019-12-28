package com.example.zip.`interface`.controller

import com.example.zip.`interface`.dto.UserDto
import com.example.zip.`interface`.request.UserRequest
import com.example.zip.`interface`.response.SimpleResponse
import com.example.zip.`interface`.response.UserResponse
import com.example.zip.const.Api
import com.example.zip.const.WebResponse
import com.example.zip.const.ZipStatus
import com.example.zip.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Api.Path.USER)
class UserController(
    @Autowired private val userService: UserService
) {

    @GetMapping
    @ResponseBody
    fun getUserList(): WebResponse {

        return userService.findAll().run {
            UserResponse(this, ZipStatus.ZIP_SUCCESS).send()
        }
    }

    @PostMapping
    @ResponseBody
    fun insertUser(@RequestBody userRequest: UserRequest): WebResponse {

        userService.save(userRequest as UserDto)

        return SimpleResponse.SUCCESS.OK.send()
    }

}