package com.sephirita.mangarift.ui.screen.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.domain.usecase.LatestUpdatesUseCase
import com.sephirita.mangarift.domain.usecase.MangaWithTitleUseCase
import com.sephirita.mangarift.domain.usecase.RecentlyAddedUseCase
import com.sephirita.mangarift.domain.usecase.SeasonUseCase
import com.sephirita.mangarift.ui.screen.search.state.SearchState
import com.sephirita.mangarift.ui.model.MangaListType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val LIMIT = 100
private const val INITIAL = ""

class SearchViewModel(
    private val getMangaWithTitleUseCase: MangaWithTitleUseCase,
    private val getRecentlyAddedUseCase: RecentlyAddedUseCase,
    private val getLatestUpdatesUseCase: LatestUpdatesUseCase,
    private val getSeasonUseCase: SeasonUseCase,
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    private var initialized = false
    private var lastSearch: String = ""

    fun search(titleToSearch: String) {
        viewModelScope.launch {
            _searchState.update { SearchState() }
            with(getMangaWithTitleUseCase(titleToSearch)) {
                onSuccess { mangas ->
                    _searchState.update {
                        SearchState(
                            isLoading = false,
                            mangaList = mangas,
                            isError = false
                        )
                    }
                    initialized = true
                }
                onFailure {
                    _searchState.update { SearchState(isLoading = false, isError = true) }
                    initialized = false
                }
            }
            lastSearch = titleToSearch
        }
    }

    fun initialSearch(initialSearch: MangaListType?) {
        if (initialized) return
        viewModelScope.launch {
            _searchState.update { SearchState() }
            when (initialSearch) {
                MangaListType.RecentlyAdded -> {
                    with(getRecentlyAddedUseCase(limit = LIMIT)) {
                        onSuccess { mangas ->
                            _searchState.update {
                                SearchState(
                                    isLoading = false,
                                    isError = false,
                                    mangaList = mangas
                                )
                            }
                            initialized = true
                        }
                        onFailure {
                            _searchState.update { SearchState(isLoading = false, isError = true) }
                            initialized = false
                        }
                    }
                }
                MangaListType.LatestUpdates -> {
                    with(getLatestUpdatesUseCase(limit = LIMIT)) {
                        onSuccess { mangas ->
                            _searchState.update {
                                SearchState(
                                    isLoading = false,
                                    isError = false,
                                    mangaList = mangas
                                )
                            }
                            initialized = true
                        }
                        onFailure {
                            _searchState.update { SearchState(isLoading = false, isError = true) }
                            initialized = false
                        }
                    }
                }
                MangaListType.Seasonal -> {
                    with(getSeasonUseCase(getAll = true)) {
                        onSuccess { mangas ->
                            _searchState.update {
                                SearchState(
                                    isLoading = false,
                                    isError = false,
                                    mangaList = mangas
                                )
                            }
                            initialized = true
                        }
                        onFailure {
                            _searchState.update { SearchState(isLoading = false, isError = true) }
                            initialized = false
                        }
                    }
                }
                else -> {
                    _searchState.update {
                        SearchState(
                            isLoading = false,
                            isError = initialSearch != null
                        )
                    }
                    initialized = true
                }
            }
            lastSearch = INITIAL
        }
    }

    fun refresh(initialSearch: MangaListType?) {
        when (lastSearch) {
            INITIAL -> {
                initialSearch?.let {
                    initialized = false
                    initialSearch(it)
                }
            }
            else -> {
                search(lastSearch)
            }
        }
    }
}