package com.example.zip.config

import org.springframework.util.StringUtils
import java.math.BigInteger

class AppConfiguration {

    var danjiUrl: String? = null
    var apartDetailUrl: String? = null
    var apartPriceUrl: String? = null

    fun isValid() = listOf(danjiUrl, apartDetailUrl, apartPriceUrl).all { StringUtils.isEmpty(it).not() }

}
