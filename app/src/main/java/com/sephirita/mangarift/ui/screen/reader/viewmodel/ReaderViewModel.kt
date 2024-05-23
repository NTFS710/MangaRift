package com.sephirita.mangarift.ui.screen.reader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.domain.usecase.ChapterPagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReaderViewModel(
    private val getChapterPagesUseCase: ChapterPagesUseCase
) : ViewModel() {

    private val _chapterPages = MutableStateFlow(listOf<String>())
    val chapterPages: StateFlow<List<String>> = _chapterPages.asStateFlow()

    fun getChapterToRead(chapterId: String) {
        viewModelScope.launch {
            _chapterPages.value = getChapterPagesUseCase(chapterId).getOrDefault(emptyList())
        }
    }
}