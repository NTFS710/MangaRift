package com.sephirita.mangarift.ui.components.banner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.domain.Manga

@Composable
fun BannersHome(items: List<Manga>) {

    val itemWidth = LocalConfiguration.current.screenWidthDp.dp + 0.55.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                items = items,
                itemContent = {
                    Banner(item = it, itemWidth)
                }
            )
        }
    }
}

