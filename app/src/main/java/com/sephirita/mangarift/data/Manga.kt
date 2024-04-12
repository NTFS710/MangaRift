package com.sephirita.mangarift.data

import com.sephirita.mangarift.ui.components.sohprateste.Tag

data class Manga (
    val id: String,
    val author: String,
    val image: String,
    val title: String,
    val rating: String,
    val tags: List<Tag>
)