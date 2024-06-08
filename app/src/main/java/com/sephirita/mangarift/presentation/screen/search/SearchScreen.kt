package com.sephirita.mangarift.presentation.screen.search

import android.annotation.SuppressLint
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.presentation.component.header.SearchHeader
import com.sephirita.mangarift.presentation.component.list.vertical.VerticalMangaList
import com.sephirita.mangarift.presentation.component.load.Loader
import com.sephirita.mangarift.presentation.model.MangaListType
import com.sephirita.mangarift.presentation.model.StateAnimationType
import com.sephirita.mangarift.presentation.screen.destinations.DetailScreenDestination
import com.sephirita.mangarift.presentation.screen.error.ErrorScreen
import com.sephirita.mangarift.presentation.screen.search.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    initialSearch: MangaListType? = null
) {
    val viewModel: SearchViewModel = koinViewModel()
    val state by viewModel.searchState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.initialSearch(initialSearch)
    }

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 48.dp)
            ) {
                SearchHeader(
                    onBackPressed = { navigator.navigateUp() },
                    onSearch = { viewModel.search(it) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                VerticalMangaList(
                    searchItems = state.mangaList,
                    detailNavigation = { navigator.navigate(DetailScreenDestination(it)) }
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
                isLoading -> {
                    Loader(StateAnimationType.FLIPPING_PAGES)
                }

                isError -> {
                    ErrorScreen(
                        enabled = state.isError,
                        onBackPressed = { navigator.navigateUp() }
                    ) { viewModel.refresh(initialSearch) }
                }
            }
        }
    }
}