package com.sephirita.mangarift.data.remote.dto.model.manga

import kotlinx.serialization.Serializable

@Serializable
data class MangaRelationshipAttributes(
    val description: String?,
    val volume: String?,
    val fileName: String?,
    val locale: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val version: Int?
)