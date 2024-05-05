package com.sephirita.mangarift.data.remote.dto.model

import kotlinx.serialization.Serializable

@Serializable
data class MainResponse(
    val result: String,
    val response: String?,
    val errors: List<Error>?,
    val data: List<Data>?,
    val limit: Int?,
    val offset: Int?,
    val total: Int?
)