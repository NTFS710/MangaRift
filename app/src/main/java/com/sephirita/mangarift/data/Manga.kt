package com.sephirita.mangarift.data

import com.sephirita.mangarift.ui.components.sohprateste.Chapter
import com.sephirita.mangarift.ui.components.sohprateste.ChapterType
import com.sephirita.mangarift.ui.components.sohprateste.Tag

data class Manga (
    val id: String,
    val author: String,
    val artist: String,
    val image: String,
    val title: String,
    val rating: String,
    val description: String,
    val tags: List<Tag>,
    val chapters: List<Chapter>
)