package com.sephirita.mangarift.ui.screen.search

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.ui.component.list.vertical.VerticalMangaList
import com.sephirita.mangarift.ui.component.load.Loader
import com.sephirita.mangarift.ui.component.search.SearchBar
import com.sephirita.mangarift.ui.model.MangaListType
import com.sephirita.mangarift.ui.model.StateAnimationType
import com.sephirita.mangarift.ui.screen.destinations.DetailsScreenDestination
import com.sephirita.mangarift.ui.screen.search.viewModel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    initialSearch: MangaListType? = null
) {
    val viewModel: SearchViewModel = koinViewModel()
    val state by viewModel.searchState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        initialSearch?.let {
            viewModel.initialSearch(initialSearch)
        }
    }

    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = { viewModel.refresh(initialSearch) }
    ) {
        with(state) {
            Scaffold(
                bottomBar = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(bottom = 8.dp)
                            .navigationBarsPadding(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        HorizontalDivider()
                    }
                }
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 48.dp)) {
                    SearchBar(onSearch = { viewModel.search(it) })
                    Spacer(modifier = Modifier.height(16.dp))
                    VerticalMangaList(
                        searchItems = state.mangaList,
                        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) }
                    )
                }
            }

            AnimatedVisibility(
                visible = isLoading || isError,
                enter = fadeIn(tween(300)),
                exit = fadeOut(tween(300)),
                modifier = Modifier.fillMaxSize()
            ) {
                when {
                    isLoading -> Loader(StateAnimationType.FLIPPING_PAGES)

                    isError -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Toast.makeText(LocalContext.current, "Deu pau", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}