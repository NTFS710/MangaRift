package com.sephirita.mangarift.ui.components.home.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.utils.toList
import com.sephirita.mangarift.utils.toMangaIdsList

class SeasonUseCase(private val api: Service) {
    suspend operator fun invoke(): List<Manga> {
        val mangaIdList = api.getSeasonMangaIds().toMangaIdsList()
        return api.getSeasonMangas(mangaIdList).toList()
    }
}