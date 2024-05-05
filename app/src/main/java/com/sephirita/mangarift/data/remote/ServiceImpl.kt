package com.sephirita.mangarift.data.remote

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.data.remote.dto.model.MangaListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class ServiceImpl(
    private val client: HttpClient
): Service {
    override suspend fun getPopularNewTitles(): MangaListResponse {
        return try {
            client.get { url(HttpRoutes.POPULAR_NEW_TITLES) }.body()
        } catch (e: Exception) {
            throw(e)
        }
    }

    override suspend fun getRecentlyAdded(): MangaListResponse {
        return try {
            client.get { url(HttpRoutes.RECENTLY_ADDED) }.body()
        } catch (e: Exception) {
            throw(e)
        }
    }

    override suspend fun getLatestUpdates(): MangaListResponse {
        return try {
            client.get { url(HttpRoutes.LATEST_UPDATES) }.body()
        } catch (e: Exception) {
            throw(e)
        }
    }

    override suspend fun getChapters(): Result<ChapterListResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMangaDetails() {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterPDF() {
        TODO("Not yet implemented")
    }
}