package com.sephirita.mangarift.data.remote.dto

import com.sephirita.mangarift.data.remote.dto.model.MainResponse

interface Service {
    // TODO Definir os parâmetros ->
    suspend fun getMangas(): Result<MainResponse>

    // TODO Definir os parâmetros ->
    suspend fun getChapters(): Result<MainResponse>

    // TODO Definir os parâmetros ??
    suspend fun getMangaDetails() // A gente vai vendo aí

    // TODO essa eu realmente não sei como fazer
    suspend fun getChapterPDF() // essa aqui é complexa
}