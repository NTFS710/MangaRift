package com.sephirita.mangarift.ui.components.search.state

import com.sephirita.mangarift.domain.model.Manga

data class SearchState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val mangaList: List<Manga> = emptyList()
)