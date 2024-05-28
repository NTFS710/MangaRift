package com.sephirita.mangarift.ui.screen.reader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.domain.usecase.ChapterPagesUseCase
import com.sephirita.mangarift.ui.screen.reader.state.ReaderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReaderViewModel(
    private val getChapterPagesUseCase: ChapterPagesUseCase
) : ViewModel() {

    private val _readerState = MutableStateFlow(ReaderState())
    val readerState: StateFlow<ReaderState> = _readerState.asStateFlow()

    fun getChapterToRead(chapterId: String) {
        viewModelScope.launch {
            _readerState.value = ReaderState(isLoading = true, isError = false)
            with(getChapterPagesUseCase(chapterId)) {
                onSuccess {
                    _readerState.value = ReaderState(
                        isLoading = false,
                        pages = it
                        // falta nextChapters
                    )
                }
                onFailure {
                    _readerState.value = ReaderState(isLoading = false, isError = true)
                }
            }
        }
    }
}