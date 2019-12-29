package com.example.zip.service

import com.example.zip.core.ZipRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RestService (
    @Autowired private val zipRestTemplate: ZipRestTemplate
) {



}