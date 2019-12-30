package com.example.zip.`interface`.dto

data class ApartDanjiDto(
    val id: String,
    val name: String,
    val local1: String,
    val local2: String,
    val local3: String,
    val bunji: String,
    val 총세대수: String,
    val 총동수: String,
    val 최고층수: String,
    val 가구당주차대수: Double,
    val 건설사: String,
    val 규제지역: String?
)