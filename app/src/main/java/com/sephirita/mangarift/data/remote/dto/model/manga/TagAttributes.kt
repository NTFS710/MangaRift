package com.sephirita.mangarift.data.remote.dto.model.manga

import kotlinx.serialization.Serializable

@Serializable
data class TagAttributes(
    val name: Map<String, String>?,
    val group: String?,
    val version: Int?
)