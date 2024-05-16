package com.sephirita.mangarift.data.remote.dto.model.chapter.page

import kotlinx.serialization.Serializable

@Serializable
data class ChapterPagesList(
    val hash: String,
    val data: List<String>,
    val dataSaver: List<String>
)