package com.sephirita.mangarift.ui.components.home

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
import com.sephirita.mangarift.ui.components.banner.BannerPager
import com.sephirita.mangarift.ui.screen.home.viewmodel.HomeViewModel
import com.sephirita.mangarift.ui.model.MangaListType
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    detailNavigation: (String) -> Unit,
    searchNavigation: (String) -> Unit
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
                        detailNavigation = { detailNavigation(it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                MangaListType.Seasonal -> {
                    val items = state.season
                    HomeItemsList(
                        listTitle = mangaType.title,
                        items = items,
                        detailNavigation = { detailNavigation(it) },
                        searchNavigation = { searchNavigation("") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                MangaListType.LatestUpdates -> {
                    val items = state.latestUpdates
                    HomeItemsList(
                        listTitle = mangaType.title,
                        items = items,
                        detailNavigation = { detailNavigation(it) },
                        searchNavigation = { searchNavigation("") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                MangaListType.RecentlyAdded -> {
                    val items = state.recentlyAdded
                    HomeItemsList(
                        listTitle = mangaType.title,
                        items = items,
                        detailNavigation = { detailNavigation(it) },
                        searchNavigation = { searchNavigation("") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}