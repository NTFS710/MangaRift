package com.sephirita.mangarift.data.model.response.manga

import com.sephirita.mangarift.data.model.response.Error

data class MangaList(
    val result: String,
    val response: String?,
    val errors: List<Error>?,
    val data: List<Manga>?,
    val limit: Int?,
    val offset: Int?,
    val total: Int?
)