package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.repository.MangaDexRepository

class LatestUpdatesUseCase(private val repository: MangaDexRepository) {
    suspend operator fun invoke(limit: Int = 15): Result<List<Manga>> {
        return repository.getLatestUpdates(limit)
    }
}