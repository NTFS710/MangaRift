package com.sephirita.mangarift.data.repository

import com.sephirita.mangarift.data.formatChapterToView
import com.sephirita.mangarift.data.formatChapters
import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.data.toList
import com.sephirita.mangarift.data.toManga
import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.repository.MangaDexRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MangaDexRepositoryImpl(private val api: Service) : MangaDexRepository {
    override suspend fun getPopularNewTitles(): Result<List<Manga>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val lastMonthDate = LocalDate.now().minusMonths(1)
                val formattedDate = "${lastMonthDate}T03:00:00"
                api.getPopularNewTitles(formattedDate).toList()
            }
        }
    }

    override suspend fun getRecentlyAdded(limit: Int): Result<List<Manga>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                api.getRecentlyAdded(limit = limit).toList()
            }
        }
    }

    override suspend fun getLatestUpdates(limit: Int): Result<List<Manga>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                api.getLatestUpdates(limit = limit).toList()
            }
        }
    }

    override suspend fun getMangaWithTitle(mangaTitle: String): Result<List<Manga>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                api.getMangaWithTitle(mangaTitle).toList()
            }
        }
    }

    override suspend fun getMangaDetails(mangaId: String): Result<Manga> {
        return withContext(Dispatchers.IO) {
            runCatching {
                api.getMangaDetails(mangaId).toManga()
            }
        }
    }

    override suspend fun getSeasonMangaIds(): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                api.getSeasonMangaIds().mangaIds
            }
        }
    }

    override suspend fun getSeasonMangas(
        mangaIdList: List<String>,
        limit: Int
    ): Result<List<Manga>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                api.getSeasonMangas(mangaIdList, limit).toList()
            }
        }
    }

    override suspend fun getChapters(mangaId: String, offset: Int): Result<Map<Float, List<Chapter>>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                var currentOffset = 0
                var chapters: ChapterListResponse = api.getChapters(mangaId, currentOffset)
                val total = chapters.total ?: 0
                currentOffset += (chapters.limit ?: 0)
                while (currentOffset < total) {
                    chapters = chapters.copy(
                        data = chapters.data + api.getChapters(mangaId, currentOffset).data
                    )
                    currentOffset += (chapters.limit ?: 0)
                }

                chapters.formatChapters()
            }
        }
    }

    override suspend fun getChapterPDF(chapterId: String): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                api.getChapterPDF(chapterId).formatChapterToView()
            }
        }
    }

}