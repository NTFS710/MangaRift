package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.formatChapters

class MangaChaptersUseCase(private val api: Service) {
    suspend operator fun invoke(id: String): Map<Float, List<Chapter>> {
        return api.getChapters(id).formatChapters()
    }
}