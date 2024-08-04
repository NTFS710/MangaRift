package com.sephirita.mangarift.presentation.screen.search.state

import com.sephirita.mangarift.domain.model.Manga

data class SearchState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val mangaList: List<Manga> = emptyList()
)