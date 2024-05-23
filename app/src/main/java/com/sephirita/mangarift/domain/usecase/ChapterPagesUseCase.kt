package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.domain.repository.MangaDexRepository

class ChapterPagesUseCase(private val repository: MangaDexRepository) {
    suspend operator fun invoke(chapterId: String): Result<List<String>> {
        return repository.getChapterPDF(chapterId)
    }
}