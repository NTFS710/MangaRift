package com.sephirita.mangarift.ui.screen.search

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    initialSearch: MangaListType? = null
) {
    val viewModel: SearchViewModel = koinViewModel()
    val searchState by viewModel.searchState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        initialSearch?.let {
            viewModel.initialSearch(initialSearch)
        }
    }

    with(searchState) {
        when {
            isLoading -> {
                Loader(StateAnimationType.FLIPPING_PAGES)
            }

            isError -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Toast.makeText(LocalContext.current, "Deu pau", Toast.LENGTH_SHORT).show()
                }
            }

            else -> {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    SearchBar(onSearch = { viewModel.search(it) })
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