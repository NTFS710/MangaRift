package com.sephirita.mangarift.data.remote.dto.model.chapter.page

import kotlinx.serialization.Serializable

@Serializable
data class ChapterPagesResponse(
    val result: String,
    val baseUrl: String,
    val chapter: ChapterPagesList
)