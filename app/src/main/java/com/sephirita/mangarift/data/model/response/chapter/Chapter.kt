package com.sephirita.mangarift.data.model.response.chapter

data class Chapter(
    val id: String,
    val type: String,
    val attributes: ChapterAttributes?,
    val relationships: List<ChapterRelationships>?
)