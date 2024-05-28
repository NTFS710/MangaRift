package com.sephirita.mangarift.ui.component.list.vertical

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.domain.model.Manga

@Composable
fun VerticalMangaList(
    modifier: Modifier = Modifier,
    searchItems: List<Manga>,
    detailNavigation: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(searchItems) {
            VerticalMangaListItem(item = it, onClick = { detailNavigation(it.id) })
        }
    }
}