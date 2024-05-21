package com.sephirita.mangarift.data.remote.dto

import androidx.compose.ui.geometry.Offset
import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.data.remote.dto.model.DetailedMangaResponse
import com.sephirita.mangarift.data.remote.dto.model.MangaListResponse
import com.sephirita.mangarift.data.remote.dto.model.SeasonResponse
import com.sephirita.mangarift.data.remote.dto.model.chapter.page.ChapterPagesResponse

interface Service {
    suspend fun getPopularNewTitles(): MangaListResponse

    suspend fun getRecentlyAdded(): MangaListResponse

    suspend fun getLatestUpdates(): MangaListResponse

    suspend fun getMangaWithTitle(mangaTitle: String): MangaListResponse

    suspend fun getMangaDetails(mangaId: String): DetailedMangaResponse

    suspend fun getSeasonMangaIds(): SeasonResponse

    suspend fun getSeasonMangas(mangaIdList: List<String>, limit: Int): MangaListResponse

    suspend fun getChapters(mangaId: String, offset: Int): ChapterListResponse

    suspend fun getChapterPDF(chapterId: String): ChapterPagesResponse
}