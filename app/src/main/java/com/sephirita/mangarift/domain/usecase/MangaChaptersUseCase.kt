package com.sephirita.mangarift.domain.usecase

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.formatChapters

class MangaChaptersUseCase(private val api: Service) {
    suspend operator fun invoke(id: String): Map<Float, List<Chapter>> {
        var currentOffset = 0
        var chapters: ChapterListResponse = api.getChapters(id, currentOffset)
        val total = chapters.total ?: 0
        currentOffset += (chapters.limit ?: 0)
        while (currentOffset < total) {
            chapters = chapters.copy(
                data = chapters.data + api.getChapters(id, currentOffset).data
            )
            currentOffset += (chapters.limit ?: 0)
        }
        return chapters.formatChapters()
    }
}