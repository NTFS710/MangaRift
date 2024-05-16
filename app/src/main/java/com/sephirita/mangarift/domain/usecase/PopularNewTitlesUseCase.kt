package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.toList

class PopularNewTitlesUseCase(private val api: Service) {
    suspend operator fun invoke(): List<Manga> {
        return api.getPopularNewTitles().toList()
    }
}