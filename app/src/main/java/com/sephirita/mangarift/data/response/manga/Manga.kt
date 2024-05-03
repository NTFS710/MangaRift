package com.sephirita.mangarift.data.response.manga

data class Manga(
    val id: String,
    val type: String?,
    val attributes: MangaAttributes?,
    val relationships: List<MangaRelationship>?
)

