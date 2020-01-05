package com.example.zip.const

import kotlin.reflect.full.isSubclassOf

class Api {

    object Path {
        const val prefix = "/zip/api"
        const val USER = "${Path.prefix}/users"
        const val APART = "${Path.prefix}/aparts"

    }
    object Param {
        const val APART_ID = "apartId"

    }
    sealed class Type(val url: String) {
        companion object {
            const val API1 = "API1"
            const val TBD = "TBD"
            const val WRITE = "WRITE"
            fun getType(url: String): Type {
                return Type::class.nestedClasses
                    .filter { it.isSubclassOf(Type::class) }
                    .map { it.objectInstance }
                    .filterIsInstance<Type>()
                    .firstOrNull { it.url == url } ?: EMPTYAPI
            }
        }

        object API1 : Type(Companion.API1)
        object TBD : Type(Companion.TBD)
        object WRITE : Type(Companion.WRITE)
        object EMPTYAPI : Type("EMPTY")
    }
}