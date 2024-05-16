package com.sephirita.mangarift.ui.components.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.ui.screen.search.viewModel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchPage(
    initialSearch: String,
    detailNavigation: (String) -> Unit
) {
    val viewModel: SearchViewModel = koinViewModel()
    val searchState by viewModel.searchState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.search(initialSearch)
    }

    Column {
        SearchBar(
            onSearch = { viewModel.search(it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchList(
            searchItems = searchState.mangaList,
            detailNavigation = { detailNavigation(it) }
        )
    }

//    with(searchState) {
//        when {
//            isLoading -> {
//
//            }
//
//            isError -> {
//
//            }
//
//            else -> {
//                Column {
//                    SearchBar(
//                        onSearch = { viewModel.search(it) }
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    SearchList(
//                        searchItems = searchState.mangaList,
//                        detailNavigation = { detailNavigation(it) }
//                    )
//                }
//            }
//        }
//    }
}