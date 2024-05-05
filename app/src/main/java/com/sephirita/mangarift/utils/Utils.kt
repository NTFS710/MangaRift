package com.sephirita.mangarift.utils

import com.sephirita.mangarift.data.remote.dto.model.MangaListResponse
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.ui.components.sohprateste.Tag
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

private const val AUTHOR_TYPE = "author"
private const val COVER_ART_TYPE = "cover_art"
private const val COVER_ART_BASE_URL = "https://mangadex.org/covers"

fun String.toDate(): String =
    OffsetDateTime.parse(
        this,
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    ).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

fun Float.formatChapterNumber(): String =
    if (this % this.toInt() > 0) this.toString() else this.toInt().toString()

fun MangaListResponse.toList(): List<Manga> {
    val mangaList: MutableList<Manga> = mutableListOf()
    data?.forEach { manga ->
        val (author, image) = findAuthorAndCover(manga)
        val title = manga.attributes?.title
        val description = manga.attributes?.description
        mangaList.add(
            Manga(
                id = manga.id,
                author = author ?: "",
                image = image ?: "",
                title = title?.get("pt-br") ?: title?.get("en") ?: "",
                rating = "8.5",
                description = description?.get("pt-br") ?: title?.get("en") ?: "",
                tags = getTags(manga.attributes?.tags),
                chapters = emptyList()
            )
        )
    }
    return mangaList
}

private fun findAuthorAndCover(manga: com.sephirita.mangarift.data.remote.dto.model.manga.Manga): Pair<String?, String?> {
    var author: String? = null
    var image: String? = null
    manga.relationships?.forEach { relationship ->
        if (relationship.type == AUTHOR_TYPE) {
            author = relationship.attributes?.name
        } else if (relationship.type == COVER_ART_TYPE) {
            image = getMangaImage(manga.id, relationship.attributes?.fileName)
        }
    }
    return Pair(author, image)
}

private fun getMangaImage(id: String, fileName: String?): String? =
    if (fileName.isNullOrBlank()) null else "$COVER_ART_BASE_URL/$id/$fileName"

private fun getTags(tags: List<com.sephirita.mangarift.data.remote.dto.model.manga.Tag>?): List<Tag> {
    val result: MutableList<Tag> = mutableListOf()
    tags?.forEach {
        val tagName = it.attributes?.name
        result.add(
            Tag(
                id = it.id,
                type = tagName?.get("pt-br") ?: tagName?.get("en") ?: ""
            )
        )
    }
    return result
}