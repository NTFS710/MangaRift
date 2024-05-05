package com.sephirita.mangarift.data.remote.dto.model

import com.sephirita.mangarift.data.remote.dto.model.chapter.Chapter
import kotlinx.serialization.Serializable

@Serializable
data class ChapterListResponse(
    val result: String,
    val response: String?,
    val errors: List<Error>?,
    val data: List<Chapter>?,
    val limit: Int?,
    val offset: Int?,
    val total: Int?
)
