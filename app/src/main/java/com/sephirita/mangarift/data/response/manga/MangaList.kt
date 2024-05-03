package com.sephirita.mangarift.data.response.manga

import com.sephirita.mangarift.data.response.Error

data class MangaList(
    val result: String,
    val response: String?,
    val errors: List<Error>?,
    val data: List<Manga>?,
    val limit: Int?,
    val offset: Int?,
    val total: Int?
)