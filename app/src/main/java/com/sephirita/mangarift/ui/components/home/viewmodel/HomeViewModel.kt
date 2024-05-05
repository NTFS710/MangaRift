package com.sephirita.mangarift.ui.components.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.ui.components.home.state.HomeState
import com.sephirita.mangarift.ui.components.home.usecase.LatestUpdatesUseCase
import com.sephirita.mangarift.ui.components.home.usecase.PopularNewTitlesUseCase
import com.sephirita.mangarift.ui.components.home.usecase.RecentlyAddedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularNewTitlesUseCase: PopularNewTitlesUseCase,
    private val getRecentlyAddedUseCase: RecentlyAddedUseCase,
    private val getLatestUpdatesUseCase: LatestUpdatesUseCase
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    private val _recentlyAddedState = MutableStateFlow(listOf(Manga()))
    val recentlyAddedList: StateFlow<List<Manga>> = _recentlyAddedState.asStateFlow()

    private val _popularNewTitlesState = MutableStateFlow(listOf(Manga()))
    val popularNewTitlesList: StateFlow<List<Manga>> = _popularNewTitlesState.asStateFlow()

    private val _latestUpdatesState = MutableStateFlow(listOf(Manga()))
    val latestUpdatesList: StateFlow<List<Manga>> = _latestUpdatesState.asStateFlow()

    fun getMangas() {
        // TODO pegar 4 ou 5 listas de manga
        viewModelScope.launch {
            _popularNewTitlesState.value = getPopularNewTitlesUseCase()
            _recentlyAddedState.value = getRecentlyAddedUseCase()
            _latestUpdatesState.value = getLatestUpdatesUseCase()
//            MangaListType.Seasonal -> {
//                println("getSeasonalUseCase()")
//            }
        }
    }
}