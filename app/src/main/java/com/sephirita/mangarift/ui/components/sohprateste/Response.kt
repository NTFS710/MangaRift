package com.sephirita.mangarift.ui.components.sohprateste

data class Response(
    val `data`: List<Data>,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int
)