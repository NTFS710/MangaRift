package com.sephirita.mangarift.domain.model

data class Chapter(
    val id: String,
    val title: String,
    val translatedLanguage: String,
    val pages: Int,
    val chapter: Float,
    val updatedAt: String,
    val scan: String
)