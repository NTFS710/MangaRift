package com.sephirita.mangarift.domain.model

data class Manga (
    val id: String = "",
    val author: String = "",
    val image: String = "",
    val title: String = "",
    val rating: String = "",
    val description: String = "",
    val tags: List<Tag> = emptyList(),
    val chapters: List<Chapter> = emptyList() // Não tô usando a lista de capitulos
)