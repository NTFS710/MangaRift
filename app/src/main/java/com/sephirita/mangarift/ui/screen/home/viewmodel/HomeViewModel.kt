package com.sephirita.mangarift.ui.screen.home.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.ui.screen.home.state.HomeState
import com.sephirita.mangarift.domain.usecase.LatestUpdatesUseCase
import com.sephirita.mangarift.domain.usecase.PopularNewTitlesUseCase
import com.sephirita.mangarift.domain.usecase.RecentlyAddedUseCase
import com.sephirita.mangarift.domain.usecase.SeasonUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularNewTitlesUseCase: PopularNewTitlesUseCase,
    private val getRecentlyAddedUseCase: RecentlyAddedUseCase,
    private val getLatestUpdatesUseCase: LatestUpdatesUseCase,
    private val getSeasonUseCase: SeasonUseCase,
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    init {
        getMangas()
    }

    private fun getMangas() {
        viewModelScope.launch {
            val popularNewTitles = async { getPopularNewTitlesUseCase() }
            val season = async { getSeasonUseCase() }
            val latestUpdates = async { getLatestUpdatesUseCase() }
            val recentlyAdded = async { getRecentlyAddedUseCase() }
            val callsResults = awaitAll(popularNewTitles, season, latestUpdates, recentlyAdded)

            _homeState.value = HomeState(
                isLoading = false,
                popularNewTitles = callsResults[0].toMutableStateList(),
                season = callsResults[1].toMutableStateList(),
                latestUpdates = callsResults[2].toMutableStateList(),
                recentlyAdded = callsResults[3].toMutableStateList()
            )
        }
    }
}