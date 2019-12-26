package com.example.zip.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class ZipConfig {

    @Value("\${config.location:classpath:config.json}")
    lateinit var configResource: Resource

    lateinit var configuration: AppConfiguration

    @Value("\${database.url:url}")
    lateinit var dbUrl: String

    @Value("\${database.user:user}")
    lateinit var dbUser: String

    @Value("\${database.pass:}")
    lateinit var dbPass: String
}