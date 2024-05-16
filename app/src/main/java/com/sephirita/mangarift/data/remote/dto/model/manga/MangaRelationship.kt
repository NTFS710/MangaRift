package com.sephirita.mangarift.data.remote.dto.model.manga

import kotlinx.serialization.Serializable

@Serializable
data class MangaRelationship(
    val id: String,
    val type: String,
    val attributes: MangaRelationshipAttributes? = null,
    val related: String? = null
)