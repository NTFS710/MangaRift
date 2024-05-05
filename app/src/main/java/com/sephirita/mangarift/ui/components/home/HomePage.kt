package com.sephirita.mangarift.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.destinations.DetailsScreenDestination
import com.sephirita.mangarift.destinations.SearchScreenDestination
import com.sephirita.mangarift.ui.components.banner.BannersHome
import com.sephirita.mangarift.utils.getMangaList

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    detailNavigation: (String) -> Unit,
    searchNavigation: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BannersHome(
            items = getMangaList(),
            detailNavigation = { detailNavigation(it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(
            listTitle = "teste 1",
            items = getMangaList(),
            detailNavigation = { detailNavigation(it) },
            searchNavigation = searchNavigation
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(
            listTitle = "teste 2",
            items = getMangaList(),
            detailNavigation = { detailNavigation(it) },
            searchNavigation = searchNavigation
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(
            listTitle = "teste 2",
            items = getMangaList(),
            detailNavigation = { detailNavigation(it) },
            searchNavigation = searchNavigation
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(
            listTitle = "teste 4",
            items = getMangaList(),
            detailNavigation = { detailNavigation(it) },
            searchNavigation = searchNavigation
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}