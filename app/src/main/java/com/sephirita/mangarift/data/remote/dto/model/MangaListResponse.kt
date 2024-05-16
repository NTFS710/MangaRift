package com.sephirita.mangarift.data.remote.dto.model

import com.sephirita.mangarift.data.remote.dto.model.manga.Manga
import kotlinx.serialization.Serializable

@Serializable
data class MangaListResponse(
    val result: String,
    val response: String? = null,
    val errors: List<Error>? = null,
    val data: List<Manga>? = null,
    val limit: Int? = null,
    val offset: Int? = null,
    val total: Int? = null
)
