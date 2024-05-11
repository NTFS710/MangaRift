package com.sephirita.mangarift.ui.components.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.ui.components.search.state.SearchState
import com.sephirita.mangarift.ui.components.search.usecase.MangaWithTitleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getMangaWithTitleUseCase: MangaWithTitleUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    fun search(titleToSearch: String) {
        viewModelScope.launch {
            _searchState.value = SearchState(
                isLoading = false,
                mangaList = getMangaWithTitleUseCase(titleToSearch),
                isError = false
            )
        }
    }
}