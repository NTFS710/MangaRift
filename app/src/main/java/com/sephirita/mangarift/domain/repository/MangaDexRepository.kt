package com.sephirita.mangarift.domain.repository

import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.model.Manga

interface MangaDexRepository {
    suspend fun getPopularNewTitles(): Result<List<Manga>>

    suspend fun getRecentlyAdded(): Result<List<Manga>>

    suspend fun getLatestUpdates(): Result<List<Manga>>

    suspend fun getMangaWithTitle(mangaTitle: String): Result<List<Manga>>

    suspend fun getMangaDetails(mangaId: String): Result<Manga>

    suspend fun getSeasonMangaIds(): Result<List<String>>

    suspend fun getSeasonMangas(mangaIdList: List<String>, limit: Int): Result<List<Manga>>

    suspend fun getChapters(mangaId: String, offset: Int): Result<Map<Float, List<Chapter>>>

    suspend fun getChapterPDF(chapterId: String): Result<List<String>>
}