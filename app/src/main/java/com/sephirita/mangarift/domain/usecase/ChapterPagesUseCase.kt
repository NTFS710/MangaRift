package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.domain.formatChapterToView

class ChapterPagesUseCase(private val api: Service) {
    suspend operator fun invoke(chapterId: String): List<String> {
        return api.getChapterPDF(chapterId).formatChapterToView()
    }
}