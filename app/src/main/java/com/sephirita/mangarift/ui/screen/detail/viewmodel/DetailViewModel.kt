package com.sephirita.mangarift.ui.screen.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.ui.screen.detail.state.DetailState
import com.sephirita.mangarift.domain.usecase.MangaChaptersUseCase
import com.sephirita.mangarift.domain.usecase.MangaDetailsUseCase
import com.sephirita.mangarift.ui.model.ChaptersOrder
import com.sephirita.mangarift.ui.model.FormatedChapters
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getMangaDetailsUseCase: MangaDetailsUseCase,
    private val getMangaChaptersUseCase: MangaChaptersUseCase
) : ViewModel() {

    private val _chaptersManga = MutableStateFlow(FormatedChapters())
    val chaptersManga: StateFlow<FormatedChapters> = _chaptersManga.asStateFlow()

    private val _detailState = MutableStateFlow(DetailState())
    val detailState: StateFlow<DetailState> = _detailState.asStateFlow()

    fun getMangaDetails(mangaId: String) {

        viewModelScope.launch {
            val callsResults = awaitAll(
                async { getMangaDetailsUseCase(mangaId) },
                async { getMangaChaptersUseCase(mangaId) }
            )

            val details = callsResults[0] as? Manga
            val chapters = (callsResults[1] as? Map<Float, List<Chapter>>)
            if (details == null || chapters == null) {
                _detailState.value = DetailState(isLoading = false, isError = true)
            } else {
                _detailState.value = DetailState(
                    isLoading = false,
                    manga = details
                )
                _chaptersManga.value = FormatedChapters(
                    order = ChaptersOrder.Natural,
                    natural = chapters,
                    reversed = chapters.toSortedMap(reverseOrder())
                )
            }
        }
    }

    fun changeOrder(order: ChaptersOrder) {
        _chaptersManga.update {
            it.copy(
                order = order
            )
        }
    }

    fun expandChapter(chapterNumber: Float) {
        val currentState = detailState.value
        val isExpanded = currentState.expandedChapter[chapterNumber] ?: false
        currentState.expandedChapter[chapterNumber] = !isExpanded
    }
}