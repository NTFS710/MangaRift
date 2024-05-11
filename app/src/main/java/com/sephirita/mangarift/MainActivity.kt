package com.sephirita.mangarift

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.destinations.DetailsScreenDestination
import com.sephirita.mangarift.destinations.ReaderScreenDestination
import com.sephirita.mangarift.destinations.SearchScreenDestination
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.ui.components.banner.Banner
import com.sephirita.mangarift.ui.components.detail.DetailPage
import com.sephirita.mangarift.ui.components.home.HomePage
import com.sephirita.mangarift.ui.components.reader.ReaderChapter
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
        searchNavigation = { navigator.navigate(SearchScreenDestination(it)) }
    )
}

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

@Destination
@Composable
fun DetailsScreen(
    navigator: DestinationsNavigator,
    id: String
) {
    DetailPage(
        id = id,
        onBackPressed = { navigator.navigateUp() },
        readerNavigation = { navigator.navigate(ReaderScreenDestination(it)) }
    )
}

@Destination
@Composable
fun ReaderScreen(
    navigator: DestinationsNavigator,
    chapterId: String
) {
    ReaderChapter(chapterId)
}

