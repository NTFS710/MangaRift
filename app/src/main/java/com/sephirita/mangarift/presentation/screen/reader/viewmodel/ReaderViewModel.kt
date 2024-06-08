package com.sephirita.mangarift.presentation.screen.reader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.domain.usecase.ChapterPagesUseCase
import com.sephirita.mangarift.presentation.screen.reader.state.ReaderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReaderViewModel(
    private val getChapterPagesUseCase: ChapterPagesUseCase
) : ViewModel() {

    private val _readerState = MutableStateFlow(ReaderState())
    val readerState: StateFlow<ReaderState> = _readerState.asStateFlow()

    fun getChapterToRead(chapterId: String) {
        viewModelScope.launch {
            _readerState.update { ReaderState(isLoading = true, isError = false) }
            with(getChapterPagesUseCase(chapterId)) {
                onSuccess { pages ->
                    _readerState.update {
                        ReaderState(
                            isLoading = false,
                            pages = pages
                            // falta nextChapters
                        )
                    }
                }
                onFailure {
                    _readerState.update { ReaderState(isLoading = false, isError = true) }
                }
            }
        }
    }
}