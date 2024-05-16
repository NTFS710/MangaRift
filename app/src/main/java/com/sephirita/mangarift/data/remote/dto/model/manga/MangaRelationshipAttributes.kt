package com.sephirita.mangarift.data.remote.dto.model.manga

import kotlinx.serialization.Serializable

@Serializable
data class MangaRelationshipAttributes(
    val name: String? = null,
    val description: String? = null,
    val volume: String? = null,
    val fileName: String? = null,
    val locale: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val version: Int? = null
)