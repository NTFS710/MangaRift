package com.sephirita.mangarift.ui.components.sohprateste

data class Chapter(
    val id: String,
    val title: String,
    val translatedLanguage: String,
    val pages: Int,
    val chapter: Float,
    val updatedAt: String
)

sealed interface ChapterType {
    data class Single(
        val chapter: Chapter
    ) : ChapterType

    data class Multiple(
        val chapter: List<Chapter>
    ) : ChapterType
}