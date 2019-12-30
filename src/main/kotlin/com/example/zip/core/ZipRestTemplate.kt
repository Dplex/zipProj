package com.example.zip.core

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URI

@Component
class ZipRestTemplate(
) {

    private val restTemplate = RestTemplate()

    fun getApiCall(uri: URI) = restTemplate.getForEntity(uri, Map::class.java)


}
