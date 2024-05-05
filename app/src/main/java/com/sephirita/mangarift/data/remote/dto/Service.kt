package com.sephirita.mangarift.data.remote.dto

import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.data.remote.dto.model.MangaListResponse

interface Service {
    // TODO Definir os parâmetros ->
    suspend fun getPopularNewTitles(): MangaListResponse

    // TODO Definir os parâmetros ->
    suspend fun getRecentlyAdded(): MangaListResponse

    // TODO Definir os parâmetros ->
    suspend fun getLatestUpdates(): MangaListResponse

    // TODO Definir os parâmetros ->
    suspend fun getChapters(): Result<ChapterListResponse>

    // TODO Definir os parâmetros ??
    suspend fun getMangaDetails() // A gente vai vendo aí

    // TODO essa eu realmente não sei como fazer
    suspend fun getChapterPDF() // essa aqui é complexa
}