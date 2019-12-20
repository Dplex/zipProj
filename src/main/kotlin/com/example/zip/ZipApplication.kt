package com.example.zip

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ZipApplication

fun main(args: Array<String>) {
    runApplication<ZipApplication>(*args)
}
