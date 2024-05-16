package com.sephirita.mangarift.data.remote.dto.model.manga

import kotlinx.serialization.Serializable

@Serializable
data class Manga(
    val id: String,
    val type: String,
    val attributes: MangaAttributes?,
    val relationships: List<MangaRelationship>?
)