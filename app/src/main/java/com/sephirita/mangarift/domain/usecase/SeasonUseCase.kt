package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.repository.MangaDexRepository

class SeasonUseCase(private val repository: MangaDexRepository) {
    suspend operator fun invoke(getAll: Boolean = false): Result<List<Manga>> {
        val mangaIdListResult = repository.getSeasonMangaIds()
        val ids = mangaIdListResult.getOrNull()
        return if (ids == null) {
            Result.failure(mangaIdListResult.exceptionOrNull() ?: Exception())
        } else {
            val limit = if (getAll) ids.size else 15
            repository.getSeasonMangas(ids, limit)
        }
    }
}