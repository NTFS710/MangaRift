package com.sephirita.mangarift.data.remote.dto.model

import com.sephirita.mangarift.data.remote.dto.model.chapter.Chapter
import kotlinx.serialization.Serializable

@Serializable
data class ChapterListResponse(
    val result: String,
    val response: String? = null,
    val errors: List<Error>? = null,
    val data: List<Chapter>,
    val limit: Int? = null,
    val offset: Int? = null,
    val total: Int? = null
)
