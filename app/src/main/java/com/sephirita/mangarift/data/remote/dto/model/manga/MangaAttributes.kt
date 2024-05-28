package com.sephirita.mangarift.data.remote.dto.model.manga

import kotlinx.serialization.Serializable

@Serializable
data class MangaAttributes(
    val title: Map<String, String>? = null,
    val altTitles: List<Map<String, String>>? = null,
    val chapterNumbersResetOnNewVolume: Boolean? = null,
    val contentRating: String? = null,
    val createdAt: String? = null,
    val description: Map<String, String>? = null,
    val isLocked: Boolean? = null,
    val lastChapter: String? = null,
    val lastVolume: String? = null,
    val latestUploadedChapter: String? = null,
    val links: Map<String, String>? = null,
    val originalLanguage: String? = null,
    val publicationDemographic: String? = null,
    val state: String? = null,
    val status: String? = null,
    val tags: List<Tag>? = null,
    val updatedAt: String? = null,
    val version: Int? = null,
    val year: Int? = null
)