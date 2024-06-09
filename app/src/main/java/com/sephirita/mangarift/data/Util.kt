package com.sephirita.mangarift.data

import com.sephirita.mangarift.R
import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.data.remote.dto.model.DetailedMangaResponse
import com.sephirita.mangarift.data.remote.dto.model.MangaListResponse
import com.sephirita.mangarift.data.remote.dto.model.chapter.page.ChapterPagesResponse
import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.model.Tag
import com.sephirita.mangarift.presentation.model.Language
import com.sephirita.mangarift.presentation.model.Language.Companion.EN
import com.sephirita.mangarift.presentation.model.Language.Companion.PT_BR

private const val AUTHOR_TYPE = "author"
private const val COVER_ART_TYPE = "cover_art"
private const val COVER_ART_BASE_URL = "https://mangadex.org/covers"

fun MangaListResponse.toList(): List<Manga> {
    val mangaList: MutableList<Manga> = mutableListOf()
    data?.forEach { manga ->
        val (author, image) = findAuthorAndCover(manga)
        val altTitles = manga.attributes?.altTitles
        val title = with(manga.attributes?.title) {
            this?.get("en") ?: this?.get("ja-ro") ?: this?.get("pt-br") ?:
            altTitles?.find { it.containsKey("en") }?.get("en") ?:
            altTitles?.find { it.containsKey("ja-ro") }?.get("ja-ro") ?:
            altTitles?.find { it.containsKey("pt-br") }?.get("pt-br") ?: ""
        }
        mangaList.add(
            Manga(
                id = manga.id,
                author = author ?: "",
                image = image ?: "",
                title = title,
                rating = "8.5", // alterar um dia
                description = "",
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
        val description = with(manga.attributes?.description) {
            this?.get("pt-br") ?: this?.get("en") ?: this?.get("ja-ro") ?: ""
        }
        val altTitles = manga.attributes?.altTitles
        val title = with(manga.attributes?.title) {
            this?.get("en") ?: this?.get("ja-ro") ?: this?.get("pt-br") ?:
            altTitles?.find { it.containsKey("en") }?.get("en") ?:
            altTitles?.find { it.containsKey("ja-ro") }?.get("ja-ro") ?:
            altTitles?.find { it.containsKey("pt-br") }?.get("pt-br") ?: ""
        }
        Manga(
            id = manga.id,
            author = author ?: "",
            image = image ?: "",
            title = title,
            rating = "8.5",
            description = description,
            tags = getTags(manga.attributes?.tags),
            chapters = emptyList()
        )
    }

    return manga ?: Manga()
}

fun ChapterListResponse.formatChapters(): Map<Float, List<Chapter>> {
    val chapters = mutableListOf<Chapter>()
    this.data.forEach { currentChapter ->
        val scanName = currentChapter.relationships?.find { it.type == "scanlation_group" }?.attributes?.name
        val languageFlag = when (currentChapter.attributes.translatedLanguage) {
            PT_BR -> R.drawable.ic_br_flag
            EN -> R.drawable.ic_en_flag
            else -> R.drawable.ic_unk_flag
        }
        chapters.add(
            Chapter(
                id = currentChapter.id,
                title = currentChapter.attributes.title ?: "",
                translatedLanguage = currentChapter.attributes.translatedLanguage ?: "",
                languageFlag = languageFlag,
                pages = currentChapter.attributes.pages ?: 0,
                chapterNumber = currentChapter.attributes.chapter ?: 0F,
                updatedAt = currentChapter.attributes.updatedAt ?: "",
                scan = scanName ?: ""
            )
        )
    }

    return chapters.groupBy { it.chapterNumber }.toSortedMap()
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