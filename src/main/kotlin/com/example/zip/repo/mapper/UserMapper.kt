package com.example.zip.repo.mapper

import com.example.zip.repo.model.UserVo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserMapper: JpaRepository<UserVo, Long> {

}