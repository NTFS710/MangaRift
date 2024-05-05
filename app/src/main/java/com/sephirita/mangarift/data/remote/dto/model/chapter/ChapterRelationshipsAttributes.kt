package com.sephirita.mangarift.data.remote.dto.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class ChapterRelationshipsAttributes(
    val name: String?,
    val altNames: List<Map<String, String>>?,
    val locked: Boolean?,
    val website: String?,
    val description: String?,
    val mangaUpdates: String?,
    val focusedLanguages: List<String>?,
    val official: Boolean?,
    val verified: Boolean?,
    val inactive: Boolean?,
    val exLicensed: Boolean?,
    val createdAt: String?,
    val updatedAt: String?,
    val version: Int?
)