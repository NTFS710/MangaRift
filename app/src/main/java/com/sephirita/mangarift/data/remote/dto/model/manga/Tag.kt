package com.sephirita.mangarift.data.remote.dto.model.manga

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: String,
    val type: String,
    val attributes: TagAttributes?
)