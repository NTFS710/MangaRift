package com.sephirita.mangarift.data.model.response.manga

data class MangaRelationship(
    val id: String,
    val type: String,
    val attributes: MangaRelationshipAttributes?,
    val related: String?
)