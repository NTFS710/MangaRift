package com.sephirita.mangarift.presentation.screen.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.presentation.screen.detail.state.DetailState
import com.sephirita.mangarift.domain.usecase.MangaChaptersUseCase
import com.sephirita.mangarift.domain.usecase.MangaDetailsUseCase
import com.sephirita.mangarift.presentation.model.ChaptersOrder
import com.sephirita.mangarift.presentation.model.FormatedChapters
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class DetailViewModel(
    private val getMangaDetailsUseCase: MangaDetailsUseCase,
    private val getMangaChaptersUseCase: MangaChaptersUseCase
) : ViewModel() {

    private val _chaptersManga = MutableStateFlow(FormatedChapters())
    val chaptersManga: StateFlow<FormatedChapters> = _chaptersManga.asStateFlow()

    private val _detailState = MutableStateFlow(DetailState())
    val detailState: StateFlow<DetailState> = _detailState.asStateFlow()

    private var fetched: Boolean = false

    fun getMangaDetails(mangaId: String) {
        if (!fetched) {
            viewModelScope.launch {
                _detailState.update { DetailState() }
                val callsResults = awaitAll(
                    async { getMangaDetailsUseCase(mangaId) } ,
                    async { getMangaChaptersUseCase(mangaId) }
                )

                val hasFailed = callsResults.any { it.isFailure }

                if (hasFailed) {
                    _detailState.update { DetailState(isLoading = false, isError = true) }
                    fetched = false
                } else {
                    callsResults[0].onSuccess { result ->
                        val manga = (result as Manga)
                        _detailState.update {
                            DetailState(
                                isLoading = false,
                                manga = manga
                            )
                        }
                    }
                    callsResults[1].onSuccess { result ->
                        val chapters = (result as Map<Float, List<Chapter>>)
                        _chaptersManga.update {
                            FormatedChapters(
                                order = ChaptersOrder.Natural,
                                natural = chapters,
                                reversed = chapters.toSortedMap(reverseOrder())
                            )
                        }
                    }
                    fetched = true
                }
            }
        }
    }

    fun refresh(mangaId: String) {
        fetched = false
        getMangaDetails(mangaId)
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