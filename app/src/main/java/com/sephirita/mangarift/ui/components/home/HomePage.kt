package com.sephirita.mangarift.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.ui.components.banner.BannersHome
import com.sephirita.mangarift.ui.components.detail.viewmodel.DetailViewModel
import com.sephirita.mangarift.ui.components.home.viewmodel.HomeViewModel
import com.sephirita.mangarift.ui.model.MangaListType
import com.sephirita.mangarift.utils.getMangaList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    detailNavigation: (String) -> Unit,
    searchNavigation: (String) -> Unit
) {

    val viewModel: HomeViewModel = koinViewModel()
    val popularNewTitlesState by viewModel.popularNewTitlesList.collectAsState()
    val recentlyAddedState by viewModel.recentlyAddedList.collectAsState()
    val latestUpdatesState by viewModel.latestUpdatesList.collectAsState()

    LaunchedEffect(
        key1 = recentlyAddedState,
        key2 = popularNewTitlesState,
        key3 = latestUpdatesState
    ) {
        viewModel.getMangas()
    }
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
                    BannersHome(
                        items = viewModel.popularNewTitlesList.value,
                        detailNavigation = { detailNavigation(it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                MangaListType.LatestUpdates -> {
                    val items = viewModel.latestUpdatesList.value
                    HomeItemsList(
                        listTitle = mangaType.title,
                        items = items,
                        detailNavigation = { detailNavigation(it) },
                        searchNavigation = { searchNavigation(mangaType.name) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                MangaListType.RecentlyAdded -> {
                    val items = viewModel.recentlyAddedList.value
                    HomeItemsList(
                        listTitle = mangaType.title,
                        items = items,
                        detailNavigation = { detailNavigation(it) },
                        searchNavigation = { searchNavigation(mangaType.name) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}