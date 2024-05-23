package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.repository.MangaDexRepository

class MangaDetailsUseCase(private val repository: MangaDexRepository) {
    suspend operator fun invoke(mangaId: String): Result<Manga> {
        return repository.getMangaDetails(mangaId)
    }
}