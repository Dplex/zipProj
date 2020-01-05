package com.example.zip.repo.model

import javax.persistence.*

@Entity
@Table(name = "TUSER")
data class UserVo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    val id: Long? = null,

    @Column(name = "USER_LOGIN_ID", unique = true, length = 22, nullable = false)
    var userLoginId: String = "",

    @Column(name = "USER_LOGIN_PASSWORD", length = 100, nullable = false)
    var userLoginPass: String = "",

    @Column(name = "USER_KEY", length = 100, nullable = true)
    var userToken: String? = null
) : UpdateMarkEntity()
