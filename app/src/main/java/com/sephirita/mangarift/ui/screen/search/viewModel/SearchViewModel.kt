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
import kotlinx.coroutines.launch

private const val LIMIT = 100

class SearchViewModel(
    private val getMangaWithTitleUseCase: MangaWithTitleUseCase,
    private val getRecentlyAddedUseCase: RecentlyAddedUseCase,
    private val getLatestUpdatesUseCase: LatestUpdatesUseCase,
    private val getSeasonUseCase: SeasonUseCase,
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    private var initialized = false

    init {
        _searchState.value = SearchState(isLoading = false)
    }

    fun search(titleToSearch: String) {
        viewModelScope.launch {
            _searchState.value = SearchState()
            with(getMangaWithTitleUseCase(titleToSearch)) {
                onSuccess {
                    _searchState.value = SearchState(
                        isLoading = false,
                        mangaList = it,
                        isError = false
                    )
                }
                onFailure {
                    _searchState.value = SearchState(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun initialSearch(initialSearch: MangaListType?) {
        if (initialized) return
        viewModelScope.launch {
            _searchState.value = SearchState()
            when (initialSearch) {
                MangaListType.RecentlyAdded -> {
                    with(getRecentlyAddedUseCase(limit = LIMIT)) {
                        onSuccess {
                            _searchState.value = SearchState(
                                isLoading = false,
                                isError = false,
                                mangaList = it
                            )
                            initialized = true
                        }
                        onFailure {
                            _searchState.value = SearchState(isLoading = false, isError = true)
                            initialized = false
                        }
                    }
                }
                MangaListType.LatestUpdates -> {
                    with(getLatestUpdatesUseCase(limit = LIMIT)) {
                        onSuccess {
                            _searchState.value = SearchState(
                                isLoading = false,
                                isError = false,
                                mangaList = it
                            )
                            initialized = true
                        }
                        onFailure {
                            _searchState.value = SearchState(isLoading = false, isError = true)
                            initialized = false
                        }
                    }
                }
                MangaListType.Seasonal -> {
                    with(getSeasonUseCase(getAll = true)) {
                        onSuccess {
                            _searchState.value = SearchState(
                                isLoading = false,
                                isError = false,
                                mangaList = it
                            )
                            initialized = true
                        }
                        onFailure {
                            _searchState.value = SearchState(isLoading = false, isError = true)
                            initialized = false
                        }
                    }
                }
                else -> {
                    _searchState.value = SearchState(isLoading = false, isError = true)
                    initialized = true
                }
            }
        }
    }
}