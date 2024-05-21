package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.toList

class SeasonUseCase(private val api: Service) {
    suspend operator fun invoke(getAll: Boolean = false): List<Manga> {
        val mangaIdList = api.getSeasonMangaIds().mangaIds
        val limit = if (getAll) mangaIdList.size else 15
        return api.getSeasonMangas(mangaIdList, limit).toList()
    }
}