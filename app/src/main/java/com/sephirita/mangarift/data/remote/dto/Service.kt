package com.sephirita.mangarift.data.remote.dto

import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.data.remote.dto.model.DetailedMangaResponse
import com.sephirita.mangarift.data.remote.dto.model.MangaListResponse
import com.sephirita.mangarift.data.remote.dto.model.chapter.page.ChapterPagesResponse

interface Service {
    suspend fun getPopularNewTitles(): MangaListResponse

    suspend fun getRecentlyAdded(): MangaListResponse

    suspend fun getLatestUpdates(): MangaListResponse

    suspend fun getMangaWithTitle(mangaTitle: String): MangaListResponse

    suspend fun getMangaDetails(mangaId: String): DetailedMangaResponse

    suspend fun getChapters(mangaId: String): ChapterListResponse

    suspend fun getChapterPDF(chapterId: String): ChapterPagesResponse
}