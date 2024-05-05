package com.sephirita.mangarift.data.remote.dto.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class ChapterAttributes(
    val volume: String?,
    val chapter: String?,
    val title: String?,
    val translatedLanguage: String?,
    val publishAt: String?,
    val readableAt: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val pages: Int?,
    val version: Int?
)