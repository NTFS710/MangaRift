package com.sephirita.mangarift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.destinations.DetailsScreenDestination
import com.sephirita.mangarift.destinations.SearchScreenDestination
import com.sephirita.mangarift.ui.components.banner.BannersHome
import com.sephirita.mangarift.ui.components.detail.DetailPage
import com.sephirita.mangarift.ui.components.home.HomeItemsList
import com.sephirita.mangarift.ui.components.search.SearchBar
import com.sephirita.mangarift.ui.components.search.SearchList
import com.sephirita.mangarift.ui.theme.MangaRiftTheme
import com.sephirita.mangarift.utils.getMangaList

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
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BannersHome(
            items = getMangaList(),
            detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(
            listTitle = "teste 1",
            items = getMangaList(),
            detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) },
            searchNavigation = { navigator.navigate(SearchScreenDestination) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(
            listTitle = "teste 2",
            items = getMangaList(),
            detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) },
            searchNavigation = { navigator.navigate(SearchScreenDestination) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(
            listTitle = "teste 2",
            items = getMangaList(),
            detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) },
            searchNavigation = { navigator.navigate(SearchScreenDestination) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(
            listTitle = "teste 4",
            items = getMangaList(),
            detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) },
            searchNavigation = { navigator.navigate(SearchScreenDestination) }
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    Column {
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        SearchList(
            searchItems = getMangaList(),
            detailNavigation = { navigator.navigate(DetailsScreenDestination(it)) }
        )
    }
}

@Destination
@Composable
fun DetailsScreen(
    navigator: DestinationsNavigator,
    id: String
) {
    DetailPage(id = id)
}