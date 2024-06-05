package com.sephirita.mangarift.ui.screen.home.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.domain.model.Manga
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
import kotlinx.coroutines.flow.update
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

    fun getMangas() {
        viewModelScope.launch {
            _homeState.update { HomeState() }
            val callsResults = awaitAll(
                async { getPopularNewTitlesUseCase() },
                async { getSeasonUseCase() },
                async { getLatestUpdatesUseCase() },
                async { getRecentlyAddedUseCase() }
            )

            val hasFailed = callsResults.any { it.isFailure }

            if (hasFailed) {
                _homeState.update { HomeState(isLoading = false, isError = true) }
            } else {
                _homeState.update {
                    val popularNewTitles =
                        callsResults[0].getOrDefault(emptyList()).toMutableStateList()
                    val season =
                        callsResults[1].getOrDefault(emptyList()).toMutableStateList()
                    val latestUpdates =
                        callsResults[2].getOrDefault(emptyList()).toMutableStateList()
                    val recentlyAdded =
                        callsResults[3].getOrDefault(emptyList()).toMutableStateList()
                    HomeState(
                        isLoading = false,
                        popularNewTitles = popularNewTitles,
                        season = season,
                        latestUpdates = latestUpdates,
                        recentlyAdded = recentlyAdded
                    )
                }
            }
        }
    }
}