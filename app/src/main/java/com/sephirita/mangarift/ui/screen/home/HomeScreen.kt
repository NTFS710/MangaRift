package com.sephirita.mangarift.ui.screen.home

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.ui.components.home.HomePage
import com.sephirita.mangarift.ui.screen.destinations.DetailsScreenDestination
import com.sephirita.mangarift.ui.screen.destinations.SearchScreenDestination

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    HomePage(
        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) },
        searchNavigation = { navigator.navigate(SearchScreenDestination(it)) }
    )
}