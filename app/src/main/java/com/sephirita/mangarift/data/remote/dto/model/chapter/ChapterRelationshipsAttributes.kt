package com.sephirita.mangarift.data.remote.dto.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class ChapterRelationshipsAttributes(
    val name: String? = null,
    val altNames: List<Map<String, String>>? = null,
    val locked: Boolean? = null,
    val website: String? = null,
    val description: String? = null,
    val mangaUpdates: String? = null,
    val focusedLanguages: List<String>? = null,
    val official: Boolean? = null,
    val verified: Boolean? = null,
    val inactive: Boolean? = null,
    val exLicensed: Boolean? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val version: Int? = null
)