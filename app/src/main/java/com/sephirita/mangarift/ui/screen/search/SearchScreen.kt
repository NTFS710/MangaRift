package com.sephirita.mangarift.ui.screen.search

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.ui.components.search.SearchPage
import com.sephirita.mangarift.ui.screen.destinations.DetailsScreenDestination

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    initialSearch: String = ""
) {
    SearchPage(
        initialSearch = initialSearch,
        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) }
    )
}