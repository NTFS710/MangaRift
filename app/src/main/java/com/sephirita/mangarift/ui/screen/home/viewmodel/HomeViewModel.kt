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
            val callsResults = awaitAll(
                async { getPopularNewTitlesUseCase().getOrDefault(emptyList()) },
                async { getSeasonUseCase().getOrDefault(emptyList()) },
                async { getLatestUpdatesUseCase().getOrDefault(emptyList()) },
                async { getRecentlyAddedUseCase().getOrDefault(emptyList()) }
            )

            val popularNewTitles = callsResults[0].toMutableStateList()
            val season = callsResults[1].toMutableStateList()
            val latestUpdates = callsResults[2].toMutableStateList()
            val recentlyAdded = callsResults[3].toMutableStateList()

            _homeState.value = HomeState(
                isLoading = false,
                popularNewTitles = popularNewTitles,
                season = season,
                latestUpdates = latestUpdates,
                recentlyAdded = recentlyAdded
            )
        }
    }
}