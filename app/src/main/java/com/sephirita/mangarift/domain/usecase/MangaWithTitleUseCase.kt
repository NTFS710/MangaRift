package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.toList

class MangaWithTitleUseCase(private val api: Service) {
    suspend operator fun invoke(mangaTitle: String): List<Manga> {
        return api.getMangaWithTitle(mangaTitle).toList()
    }
}