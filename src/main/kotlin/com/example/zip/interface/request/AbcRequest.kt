package com.example.zip.`interface`.request

abstract class AbcRequest {
    open fun valid(): Boolean {
        return true
    }
}