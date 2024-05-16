package com.sephirita.mangarift.domain

import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.data.remote.dto.model.DetailedMangaResponse
import com.sephirita.mangarift.data.remote.dto.model.MangaListResponse
import com.sephirita.mangarift.data.remote.dto.model.chapter.page.ChapterPagesResponse
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.model.Tag
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

fun DetailedMangaResponse.toManga(): Manga {
    val manga = data?.let { manga ->
        val (author, image) = findAuthorAndCover(manga)
        val title = manga.attributes?.title
        val description = manga.attributes?.description
        Manga(
            id = manga.id,
            author = author ?: "",
            image = image ?: "",
            title = title?.get("pt-br") ?: title?.get("en") ?: "",
            rating = "8.5",
            description = description?.get("pt-br") ?: description?.get("en") ?: "",
            tags = getTags(manga.attributes?.tags),
            chapters = emptyList()
        )
    }


    return manga ?: Manga()
}

fun DetailedMangaResponse.toMangaIdsList(): List<String> {
    val mangaIds = mutableListOf<String>()
    data?.relationships?.forEach { mangaIds.add(it.id) }
    return mangaIds
}

fun ChapterListResponse.formatChapters(): Map<Float, List<Chapter>> {

    val chapters = mutableListOf<Chapter>()
    this.data.forEach { currentChapter ->
        val scanName = currentChapter.relationships?.find { it.type == "scanlation_group" }?.attributes?.name
        chapters.add(
            Chapter(
                id = currentChapter.id,
                title = currentChapter.attributes.title ?: "",
                translatedLanguage = currentChapter.attributes.translatedLanguage ?: "",
                pages = currentChapter.attributes.pages ?: 0,
                chapter = currentChapter.attributes.chapter ?: 0F,
                updatedAt = currentChapter.attributes.updatedAt ?: "",
                scan = scanName ?: ""
            )
        )
    }

    return chapters.groupBy { it.chapter }.toSortedMap()
}

fun ChapterPagesResponse.formatChapterToView(): List<String> {
    val chapterPages = mutableListOf<String>()
    chapter.data.forEach {
        chapterPages.add(
            "$baseUrl/data/${chapter.hash}/$it"
        )
    }
    return chapterPages
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