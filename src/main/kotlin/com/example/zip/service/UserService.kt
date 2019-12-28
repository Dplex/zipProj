package com.example.zip.service

import com.example.zip.`interface`.dto.UserDto
import com.example.zip.repo.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userMapper: UserMapper
) {

    fun findAll() = userMapper.findAll().map { UserDto(it) }

    fun save(userDto: UserDto) {
        userMapper.save(userDto.toUserVo())
    }

}