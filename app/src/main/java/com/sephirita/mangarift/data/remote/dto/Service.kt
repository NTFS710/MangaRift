package com.sephirita.mangarift.data.remote.dto

import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.data.remote.dto.model.DetailedMangaResponse
import com.sephirita.mangarift.data.remote.dto.model.MangaListResponse

interface Service {
    // TODO Definir os parâmetros ->
    suspend fun getPopularNewTitles(): MangaListResponse

    // TODO Definir os parâmetros ->
    suspend fun getRecentlyAdded(): MangaListResponse

    // TODO Definir os parâmetros ->
    suspend fun getLatestUpdates(): MangaListResponse

    suspend fun getChapters(id: String): ChapterListResponse

    // TODO Definir os parâmetros ??
    suspend fun getMangaDetails(id: String): DetailedMangaResponse // A gente vai vendo aí

    // TODO essa eu realmente não sei como fazer
    suspend fun getChapterPDF() // essa aqui é complexa
}