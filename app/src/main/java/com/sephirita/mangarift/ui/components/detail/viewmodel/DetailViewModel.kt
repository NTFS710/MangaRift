package com.sephirita.mangarift.ui.components.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.ui.components.detail.state.DetailState
import com.sephirita.mangarift.ui.components.detail.usecase.MangaChaptersUseCase
import com.sephirita.mangarift.ui.components.detail.usecase.MangaDetailsUseCase
import com.sephirita.mangarift.ui.model.ChaptersOrder
import com.sephirita.mangarift.ui.model.FormatedChapters
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
        // TODO fazer a chamada pra api : Loading ; Sucesso ; Error
        viewModelScope.launch {
            val result = getMangaDetailsUseCase(mangaId)
            val chapters = getMangaChaptersUseCase(mangaId)
            _detailState.value = DetailState(
                isLoading = false,
                manga = result
            )
            _chaptersManga.value = FormatedChapters(
                order = ChaptersOrder.Natural,
                natural = chapters,
                reversed = chapters.toSortedMap(reverseOrder())
            )
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