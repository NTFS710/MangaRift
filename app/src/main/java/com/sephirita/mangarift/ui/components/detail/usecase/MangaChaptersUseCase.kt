package com.sephirita.mangarift.ui.components.detail.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.ui.components.sohprateste.Chapter
import com.sephirita.mangarift.utils.formatChapters

class MangaChaptersUseCase(private val api: Service) {
    suspend operator fun invoke(id: String): Map<Float, List<Chapter>> {
        return api.getChapters(id).formatChapters()
    }
}