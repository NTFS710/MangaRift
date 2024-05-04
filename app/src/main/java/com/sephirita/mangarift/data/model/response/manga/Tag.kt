package com.sephirita.mangarift.data.model.response.manga

data class Tag(
    val id: String,
    val type: String,
    val attributes: TagAttributes?
)