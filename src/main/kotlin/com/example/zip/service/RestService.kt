package com.example.zip.service

import com.example.zip.`interface`.dto.ApartDanjiDto
import com.example.zip.config.ZipConfig
import com.example.zip.core.ZipRestTemplate
import com.example.zip.util.GsonUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URI

@Service
class RestService (
    @Autowired private val zipRestTemplate: ZipRestTemplate,
    @Autowired private val zipConfig: ZipConfig
) {

    val objectMapper = ObjectMapper()

    fun danjiInfo(danjiId: String) {

        val uri = URI(zipConfig.configuration.danjiUrl?.format(danjiId))

        zipRestTemplate.getApiCall(uri)?.body?.let {
            val dto = GsonUtil.fromMap(it, ApartDanjiDto::class.java)
            print(dto)
        }


    }


}

