package com.sephirita.mangarift.data.model.response

data class Error(
    val id: String,
    val status: Int?,
    val title: String?,
    val detail: String?
)