package com.sephirita.mangarift.data.remote.dto.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class ChapterRelationships(
    val id: String,
    val type: String,
    val attributes: ChapterRelationshipsAttributes? = null
)