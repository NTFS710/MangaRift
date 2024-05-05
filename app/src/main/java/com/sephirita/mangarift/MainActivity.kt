package com.sephirita.mangarift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.destinations.DetailsScreenDestination
import com.sephirita.mangarift.destinations.SearchScreenDestination
import com.sephirita.mangarift.ui.components.detail.DetailPage
import com.sephirita.mangarift.ui.components.home.HomePage
import com.sephirita.mangarift.ui.components.search.SearchPage
import com.sephirita.mangarift.ui.theme.MangaRiftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MangaRiftTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
//                    navigator.popBackStack() com click duplo fica bugado
                }
            }
        }
    }
}

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    HomePage(
        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) },
        searchNavigation = { navigator.navigate(SearchScreenDestination) }
    )
}

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    SearchPage(
        detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) }
    )
}

@Destination
@Composable
fun DetailsScreen(
    navigator: DestinationsNavigator,
    id: String
) {
    DetailPage(
        id = id,
        onBackPressed = { navigator.popBackStack() }
    )
}