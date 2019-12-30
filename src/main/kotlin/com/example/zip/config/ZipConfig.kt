package com.example.zip.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.InputStreamReader
import javax.annotation.PostConstruct

@Component
class ZipConfig {

    @Value("\${config.location:classpath:config.json}")
    lateinit var configResource: Resource

    lateinit var configuration: AppConfiguration

    @Value("\${database.url:url}")
    lateinit var dbUrl: String

    @Value("\${database.user:user}")
    lateinit var dbUser: String

    @Value("\${database.pass:pass}")
    lateinit var dbPass: String

    @PostConstruct
    fun init() {
        ObjectMapper().run {
            readValue(InputStreamReader(configResource.inputStream), AppConfiguration::class.java).run {
                if (this.isValid())
                    configuration = this
            }
        }
    }

}