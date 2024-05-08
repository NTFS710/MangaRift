package com.sephirita.mangarift.ui.components.detail.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.utils.formatChapterToView

class ChapterPagesUseCase(private val api: Service) {
    suspend operator fun invoke(chapterId: String): List<String> {
        return api.getChapterPDF(chapterId).formatChapterToView()
    }
}