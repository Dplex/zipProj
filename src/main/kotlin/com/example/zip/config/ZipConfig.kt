package com.example.zip.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ZipConfig {

    @Value("\${database.url}")
    lateinit var dbUrl: String

    @Value("\${database.user}")
    lateinit var dbUser: String

    @Value("\${database.pass}")
    lateinit var dbPassword: String

}
