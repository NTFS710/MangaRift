package com.sephirita.mangarift.data.response

data class MainResponse(
    val result: String,
    val response: String?,
    val errors: List<Error>?,
    val data: List<String>?, // Vamo vendo aí
    val limit: Int?,
    val offset: Int?,
    val total: Int?
)