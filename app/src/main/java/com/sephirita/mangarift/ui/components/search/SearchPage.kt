package com.sephirita.mangarift.ui.components.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.destinations.DetailsScreenDestination
import com.sephirita.mangarift.utils.getMangaList

@Composable
fun SearchPage(
    modifier: Modifier = Modifier,
    detailNavigation: (String) -> Unit
) {
    Column {
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        SearchList(
            searchItems = getMangaList(),
            detailNavigation = { detailNavigation(it) }
        )
    }
}