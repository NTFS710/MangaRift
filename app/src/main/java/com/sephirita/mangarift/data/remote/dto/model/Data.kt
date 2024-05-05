package com.sephirita.mangarift.data.remote.dto.model

import com.sephirita.mangarift.data.remote.dto.model.chapter.ChapterAttributes
import com.sephirita.mangarift.data.remote.dto.model.chapter.ChapterRelationships
import com.sephirita.mangarift.data.remote.dto.model.manga.MangaAttributes
import com.sephirita.mangarift.data.remote.dto.model.manga.MangaRelationship
import kotlinx.serialization.Serializable

@Serializable
sealed class Data {
    @Serializable
    data class Chapter(
        val id: String,
        val type: String,
        val attributes: ChapterAttributes?,
        val relationships: List<ChapterRelationships>?
    ): Data()

    @Serializable
    data class Manga(
        val id: String,
        val type: String,
        val attributes: MangaAttributes?,
        val relationships: List<MangaRelationship>?
    ): Data()
}