package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.repository.MangaDexRepository

class RecentlyAddedUseCase(private val repository: MangaDexRepository) {
    suspend operator fun invoke(): Result<List<Manga>> {
        return repository.getRecentlyAdded()
    }
}