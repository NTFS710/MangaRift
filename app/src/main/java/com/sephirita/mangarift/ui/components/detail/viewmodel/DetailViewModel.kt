package com.sephirita.mangarift.ui.components.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.ui.components.detail.state.DetailState
import com.sephirita.mangarift.ui.components.detail.usecase.MangaChaptersUseCase
import com.sephirita.mangarift.ui.components.detail.usecase.MangaDetailsUseCase
import com.sephirita.mangarift.ui.components.sohprateste.Chapter
import com.sephirita.mangarift.ui.model.FormatedChapters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getMangaDetailsUseCase: MangaDetailsUseCase,
    private val getMangaChaptersUseCase: MangaChaptersUseCase
) : ViewModel() {

    private val _chaptersManga = MutableStateFlow(FormatedChapters())

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
                chapters = chapters
            )
        }
    }

    fun getChapters(): Map<Float, List<Chapter>> = _chaptersManga.value.chapters

    fun expandChapter(chapterNumber: Float) {
        val currentState = detailState.value
        val isExpanded = currentState.expandedChapter[chapterNumber] ?: false
        currentState.expandedChapter[chapterNumber] = !isExpanded
    }
}