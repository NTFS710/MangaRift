package com.sephirita.mangarift.data.remote

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.data.remote.dto.model.MainResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class ServiceImpl(
    private val client: HttpClient
): Service {
    override suspend fun getMangas(): Result<MainResponse> {
        return try {
            val response = client.get { url(HttpRoutes.MANGA) }
            Result.success(response.body())
        } catch (e: Exception) {
            println("Erro ")
            Result.failure(e)
        }
    }

    override suspend fun getChapters(): Result<MainResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMangaDetails() {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterPDF() {
        TODO("Not yet implemented")
    }
}