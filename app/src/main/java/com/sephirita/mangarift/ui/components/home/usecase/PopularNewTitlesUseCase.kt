package com.sephirita.mangarift.ui.components.home.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.utils.toList

class PopularNewTitlesUseCase(private val api: Service) {
    suspend operator fun invoke(): List<Manga> {
        return api.getPopularNewTitles().toList()
    }
}