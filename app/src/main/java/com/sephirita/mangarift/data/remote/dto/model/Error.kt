package com.sephirita.mangarift.data.remote.dto.model

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val id: String,
    val status: Int?,
    val title: String?,
    val detail: String?
)