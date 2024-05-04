package com.sephirita.mangarift.data.model.response.chapter

data class ChapterRelationships(
    val id: String,
    val type: String,
    val attributes: ChapterRelationshipsAttributes?
)