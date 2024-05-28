package com.sephirita.mangarift.ui.component.detail

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
import com.sephirita.mangarift.domain.model.Tag
import com.sephirita.mangarift.ui.component.chapter.ChaptersSection
import com.sephirita.mangarift.ui.model.ChaptersOrder
import com.sephirita.mangarift.ui.model.DetailsTab
import com.sephirita.mangarift.ui.model.FormatedChapters
import kotlinx.coroutines.launch

@Composable
fun DetailsPager(
    modifier: Modifier = Modifier,
    tags: List<Tag>,
    description: String,
    chapters: FormatedChapters,
    changeChaptersOrder: (ChaptersOrder) -> Unit,
    expandedChapterList: Map<Float, Boolean>,
    expandChapterCallback: (Float) -> Unit,
    readerNavigation: (String) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { DetailsTab.entries.size })
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
                    DetailsTab.entries.forEach {
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
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize(tween(300)),
                verticalAlignment = Alignment.Top,
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    DetailsTab.Details.index -> DetailsSection(
                        tags = tags,
                        description = description
                    )
                    DetailsTab.Chapters.index -> ChaptersSection(
                        chapters = chapters,
                        changeChaptersOrder = changeChaptersOrder,
                        expandedChapterList = expandedChapterList,
                        expandChapterCallback = expandChapterCallback,
                        readerNavigation = readerNavigation
                    )
                }
            }
        }
    }
}