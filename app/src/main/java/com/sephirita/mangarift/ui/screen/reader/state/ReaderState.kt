package com.sephirita.mangarift.ui.screen.reader.state

data class ReaderState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val pages: List<String> = emptyList(),
    val nextChapters: List<String> = emptyList()
)
