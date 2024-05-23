package com.sephirita.mangarift.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.ui.component.list.vertical.VerticalMangaList
import com.sephirita.mangarift.ui.component.load.Loader
import com.sephirita.mangarift.ui.component.search.SearchBar
import com.sephirita.mangarift.ui.model.StateAnimationType
import com.sephirita.mangarift.ui.screen.destinations.DetailsScreenDestination
import com.sephirita.mangarift.ui.screen.search.viewModel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    initialSearch: String = "" // passar o MangaListType
) {
    val viewModel: SearchViewModel = koinViewModel()
    val searchState by viewModel.searchState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.search(initialSearch)
    }
    with(searchState) {
        when {
            isLoading -> {
                Loader(StateAnimationType.FLIPPING_PAGES)
            }

            isError -> {
                println("deu erro")
            }

            else -> {
                Column {
                    SearchBar(
                        onSearch = { viewModel.search(it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    VerticalMangaList(
                        searchItems = searchState.mangaList,
                        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) }
                    )
                }
            }
        }
    }
}