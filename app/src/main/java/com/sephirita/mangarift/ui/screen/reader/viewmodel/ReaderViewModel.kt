package com.sephirita.mangarift.ui.screen.reader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.domain.usecase.ChapterPagesUseCase
import com.sephirita.mangarift.ui.screen.reader.state.ReaderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.lang.Thread.sleep

class ReaderViewModel(
    private val getChapterPagesUseCase: ChapterPagesUseCase
) : ViewModel() {

    private val _readerState = MutableStateFlow(ReaderState())
    val readerState: StateFlow<ReaderState> = _readerState.asStateFlow()

    var count: Int = 0

    fun getChapterToRead(chapterId: String) {
        viewModelScope.launch {
            _readerState.value = ReaderState(isLoading = true)
            with(getChapterPagesUseCase(chapterId, count = if (count == 3) 3 else 0)) {
                count++
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