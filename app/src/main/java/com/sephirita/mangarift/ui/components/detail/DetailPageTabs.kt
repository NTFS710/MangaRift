package com.sephirita.mangarift.ui.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.data.Manga
import com.sephirita.mangarift.ui.model.DetailsPageTab
import kotlinx.coroutines.launch

@Composable
fun DetailPageTabs(
    modifier: Modifier = Modifier,
    item: Manga
) {
    val pagerState = rememberPagerState(pageCount = { DetailsPageTab.entries.size })
    val coroutineScope = rememberCoroutineScope()
    val error = false

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = MaterialTheme.colorScheme.background,
                divider = {},
                indicator = {},
                tabs = {
                    DetailsPageTab.entries.forEach {
                        Tab(
                            modifier = Modifier
                                .clip(it.getCorners())
                                .background(
                                    if (pagerState.currentPage == it.index)
                                        MaterialTheme.colorScheme.background
                                    else
                                        MaterialTheme.colorScheme.surface
                                ),
                            selected = pagerState.currentPage == it.index,
                            text = {
                                Text(
                                    text = it.getText(),
                                    color = if (pagerState.currentPage == it.index) White else Gray,
                                    fontSize = 18.sp
                                )
                            },
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(it.index)
                                }
                            }
                        )
                    }
                }
            )
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    DetailsPageTab.Details.index -> DetailedInformation(item = item)
                    DetailsPageTab.Chapters.index -> ChaptersList(chapters = item.chapters)
                }
            }
        }
    }
}