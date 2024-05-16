package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.toManga

class MangaDetailsUseCase(private val api: Service) {
    suspend operator fun invoke(mangaId: String): Manga {
        return api.getMangaDetails(mangaId).toManga()
    }
}