package com.example.zip.util

import com.example.zip.core.aop.Transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggerUtil {
    companion object {
        inline fun <reified T> logger() = LoggerFactory.getLogger(T::class.java)
        private val JSON_LOGGER = LoggerFactory.getLogger("JSON")


        fun errorLog(exception: Exception) {

        }

        fun transactionLog(transaction: Transaction) {
            JSON_LOGGER.info(GsonUtil.toJson(transaction))
        }
    }
}