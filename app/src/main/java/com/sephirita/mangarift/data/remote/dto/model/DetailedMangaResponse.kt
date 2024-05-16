package com.sephirita.mangarift.data.remote.dto.model

import com.sephirita.mangarift.data.remote.dto.model.manga.Manga
import kotlinx.serialization.Serializable

@Serializable
data class DetailedMangaResponse(
    val result: String,
    val response: String? = null,
    val errors: List<Error>? = null,
    val data: Manga? = null,
    val limit: Int? = null,
    val offset: Int? = null,
    val total: Int? = null
)