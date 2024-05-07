package com.sephirita.mangarift.data.remote.dto.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    val id: String,
    val type: String,
    val attributes: ChapterAttributes,
    val relationships: List<ChapterRelationships>?
)