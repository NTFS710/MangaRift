package com.sephirita.mangarift.ui.components.banner

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.domain.model.Manga

@Composable
fun BannersHome(
    items: List<Manga>,
    detailNavigation: (String) -> Unit
) {

    val pagerState = rememberPagerState(pageCount = { items.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
    ) {
        val currentItem = items[it]
        Banner(item = currentItem, onClick = { detailNavigation(currentItem.id) })
    }
}

