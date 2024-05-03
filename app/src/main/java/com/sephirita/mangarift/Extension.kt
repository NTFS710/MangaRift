package com.sephirita.mangarift

import com.sephirita.mangarift.domain.model.Manga

private const val COVERS_INIT_LINK = "https://mangadex.org/covers"


fun Manga.getCoverUrl(): String {
    val coverUrl = "$COVERS_INIT_LINK/$id/manga.relationships.filter { type == cover_art }.fileName"
    return "https://mangadex.org/covers/dd0b8df1-1b59-4ce3-a6f3-cc19cf6ca4f6/334123d5-8f81-479e-a3b4-0ec9eb29bf5e.jpg"
}

