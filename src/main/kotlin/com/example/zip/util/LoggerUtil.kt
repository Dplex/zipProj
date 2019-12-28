package com.example.zip.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggerUtil {
    companion object {
        inline fun <reified T> logger() = LoggerFactory.getLogger(T::class.java)
    }
}