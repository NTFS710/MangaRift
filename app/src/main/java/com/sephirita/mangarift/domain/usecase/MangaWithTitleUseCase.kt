package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.domain.repository.MangaDexRepository

class MangaWithTitleUseCase(private val repository: MangaDexRepository) {
    suspend operator fun invoke(mangaTitle: String): Result<List<Manga>> {
        return repository.getMangaWithTitle(mangaTitle)
    }
}