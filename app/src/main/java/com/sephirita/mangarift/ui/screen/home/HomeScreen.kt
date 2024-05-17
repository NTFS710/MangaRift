package com.sephirita.mangarift.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.ui.component.banner.BannerPager
import com.sephirita.mangarift.ui.component.list.horizontal.HorizontalMangaList
import com.sephirita.mangarift.ui.model.MangaListType
import com.sephirita.mangarift.ui.screen.destinations.DetailsScreenDestination
import com.sephirita.mangarift.ui.screen.destinations.SearchScreenDestination
import com.sephirita.mangarift.ui.screen.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.homeState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MangaListType.entries.forEach { mangaType ->
            when (mangaType) {
                MangaListType.PopularNewTitles -> {
                    BannerPager(
                        items = state.popularNewTitles,
                        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                MangaListType.Seasonal -> {
                    val items = state.season
                    HorizontalMangaList(
                        listTitle = mangaType.title,
                        items = items,
                        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) },
                        searchNavigation = { navigator.navigate(SearchScreenDestination("")) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                MangaListType.LatestUpdates -> {
                    val items = state.latestUpdates
                    HorizontalMangaList(
                        listTitle = mangaType.title,
                        items = items,
                        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) },
                        searchNavigation = { navigator.navigate(SearchScreenDestination("")) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                MangaListType.RecentlyAdded -> {
                    val items = state.recentlyAdded
                    HorizontalMangaList(
                        listTitle = mangaType.title,
                        items = items,
                        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) },
                        searchNavigation = { navigator.navigate(SearchScreenDestination("")) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}