package com.sephirita.mangarift.ui.components.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.ui.components.detail.state.DetailState
import com.sephirita.mangarift.ui.components.sohprateste.Chapter
import com.sephirita.mangarift.ui.model.FormatedChapters
import com.sephirita.mangarift.utils.getMockedManga
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.SortedMap

class DetailViewModel : ViewModel() {

    private val _chaptersManga = MutableStateFlow(FormatedChapters())

    private val _detailState = MutableStateFlow(DetailState())
    val detailState: StateFlow<DetailState> = _detailState.asStateFlow()

    fun getMangaDetails(mangaId: String) {
        // TODO fazer a chamada pra api : Loading ; Sucesso ; Error
        viewModelScope.launch {
            val manga = getMockedManga()
            _detailState.value = DetailState(
                isLoading = false,
                manga = manga
            )
            _chaptersManga.value = FormatedChapters(
                chapters = setChapters(manga.chapters)
            )
        }
    }

    fun getChapters(): Map<Float, List<Chapter>> = _chaptersManga.value.chapters

    private fun setChapters(chapters: List<Chapter>): SortedMap<Float, List<Chapter>> =
       chapters.groupBy { it.chapter }.toSortedMap(naturalOrder())


    fun expandChapter(chapterNumber: Float) {
        val currentState = detailState.value
        val isExpanded = currentState.expandedChapter[chapterNumber] ?: false
        currentState.expandedChapter[chapterNumber] = !isExpanded
    }
}