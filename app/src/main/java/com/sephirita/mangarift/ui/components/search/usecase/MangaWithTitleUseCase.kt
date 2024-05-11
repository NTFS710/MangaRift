package com.sephirita.mangarift.ui.components.search.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.utils.toList

class MangaWithTitleUseCase(private val api: Service) {
    suspend operator fun invoke(mangaTitle: String): List<Manga> {
        return api.getMangaWithTitle(mangaTitle).toList()
    }
}