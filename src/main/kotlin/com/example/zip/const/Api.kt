package com.example.zip.const

class Api {
    object Path {
        private const val prefix = "/zip/api"
        const val USER = "$prefix/users"
        const val APART = "$prefix/aparts"
    }

    object Param {
        const val APART_ID = "apartId"
    }
}