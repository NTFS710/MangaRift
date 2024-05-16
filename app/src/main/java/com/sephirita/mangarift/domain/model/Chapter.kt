package com.sephirita.mangarift.domain.model

data class Chapter(
    val id: String,
    val title: String,
    val translatedLanguage: String,
    val pages: Int,
    val chapterNumber: Float,
    val updatedAt: String,
    val scan: String,
    val languageFlag: Int
)