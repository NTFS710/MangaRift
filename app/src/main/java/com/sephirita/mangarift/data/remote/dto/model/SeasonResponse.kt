package com.sephirita.mangarift.data.remote.dto.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonResponse(
    val id: String? = null,
    val name: String? = null,
    @SerialName("manga_ids")
    val mangaIds: List<String> = emptyList()
)
