package com.sephirita.mangarift.data.response.manga

data class TagAttributes(
    val name: List<Map<String, String>>?,
    val group: String?,
    val version: Int?
)