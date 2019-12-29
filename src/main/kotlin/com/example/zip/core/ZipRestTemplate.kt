package com.example.zip.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Component
class ZipRestTemplate(
) {

    private val restTemplate = RestTemplate()

    fun <T> getApiCall(url: String, uriVars: Map<String, Any>, type: Class<T>): T? {
        return restTemplate.getForObject(url, type, uriVars)
    }


}
