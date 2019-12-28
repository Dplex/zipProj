package com.example.zip.const

import org.springframework.http.HttpStatus

enum class ZipStatus(
    val code: String,
    val message: String,
    val status: HttpStatus
) {
    ZIP_SUCCESS("ZIP201", "SUCCESS", HttpStatus.OK),
    ZIP_ACCEPTED("ZIP202", "ACCEPTED", HttpStatus.ACCEPTED),
    ZIP_BAD_REQUEST("ZIP401", "BAD_REQUEST", HttpStatus.BAD_REQUEST)
}