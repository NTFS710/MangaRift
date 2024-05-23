package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.repository.MangaDexRepository

class MangaChaptersUseCase(private val repository: MangaDexRepository) {
    suspend operator fun invoke(id: String): Result<Map<Float, List<Chapter>>> {
        return repository.getChapters(id, 0)
    }
}