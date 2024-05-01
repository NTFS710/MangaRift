package com.sephirita.mangarift.ui.components.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import com.sephirita.mangarift.ui.components.sohprateste.Chapter
import com.sephirita.mangarift.ui.components.sohprateste.Tag
import com.sephirita.mangarift.ui.model.DetailsPageTab
import kotlinx.coroutines.launch
import java.util.SortedMap

@Composable
fun DetailPageTabs(
    modifier: Modifier = Modifier,
    tags: List<Tag>,
    description: String,
    chaptersList: Map<Float, List<Chapter>>,
    expandedChapterList: Map<Float, Boolean>,
//    sortChaptersCallback: (Boolean) -> Unit,
    expandChapterCallback: (Float) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { DetailsPageTab.entries.size })
    val coroutineScope = rememberCoroutineScope()

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
                modifier = Modifier.fillMaxSize().animateContentSize(tween(300)),
                verticalAlignment = Alignment.Top,
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    DetailsPageTab.Details.index -> DetailedInformation(
                        tags = tags,
                        description = description
                    )
                    DetailsPageTab.Chapters.index -> ChaptersList(
                        chaptersList = chaptersList,
                        expandedChapterList = expandedChapterList,
//                        sortChaptersCallback = sortChaptersCallback,
                        expandChapterCallback = expandChapterCallback
                    )
                }
            }
        }
    }
}