package com.sephirita.mangarift.ui.components.detail.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.utils.toManga

class MangaDetailsUseCase(private val api: Service) {
    suspend operator fun invoke(mangaId: String): Manga {
        return api.getMangaDetails(mangaId).toManga()
    }
}