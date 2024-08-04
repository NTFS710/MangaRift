package com.sephirita.mangarift.presentation.screen.detail

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.presentation.component.card.TagSection
import com.sephirita.mangarift.presentation.component.chapter.ChaptersList
import com.sephirita.mangarift.presentation.component.load.Loader
import com.sephirita.mangarift.presentation.component.scaffold.ScrollableScreenContainer
import com.sephirita.mangarift.presentation.model.ChaptersOrder
import com.sephirita.mangarift.presentation.model.DetailsTab
import com.sephirita.mangarift.presentation.model.DetailsTab.Chapters
import com.sephirita.mangarift.presentation.model.DetailsTab.Details
import com.sephirita.mangarift.presentation.model.StateAnimationType
import com.sephirita.mangarift.presentation.screen.destinations.ReaderScreenDestination
import com.sephirita.mangarift.presentation.screen.detail.viewmodel.DetailViewModel
import com.sephirita.mangarift.presentation.screen.error.ErrorScreen
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    id: String
) {
    val viewModel: DetailViewModel = koinViewModel()
    val detailState by viewModel.detailState.collectAsState()
    val chaptersState by viewModel.chaptersManga.collectAsState()

    var pagerState by remember { mutableIntStateOf(Details.index) }
    val chaptersList = if (chaptersState.order == ChaptersOrder.Natural)
        chaptersState.natural else chaptersState.reversed

    LaunchedEffect(Unit) {
        viewModel.getMangaDetails(id)
    }

    with(detailState) {
        ScrollableScreenContainer(
            onNavigateBack = { navigator.navigateUp() },
            imageUri = manga.image,
            title = manga.title,
            bottomBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorScheme.background)
                        .navigationBarsPadding()
                ) {
                    HorizontalDivider()
                }
            }
        ) {offset ->
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorScheme.background)
                        .offset(y = -offset)
                        .clip(RoundedCornerShape(topStart = offset, topEnd = offset))
                ) {
                    DetailsTab.entries.forEach {
                        Box(
                            modifier = Modifier
                                .clip(it.getCorners())
                                .background(
                                    if (pagerState == it.index)
                                        colorScheme.background
                                    else
                                        colorScheme.surface
                                )
                                .clickable {
                                    pagerState = it.index
                                }
                                .height(50.dp)
                                .weight(0.5f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it.getText(),
                                color = if (pagerState == it.index) Color.White else Color.Gray,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }

            when (pagerState) {
                Details.index -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colorScheme.background)
                                .navigationBarsPadding()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .offset(y = -offset)
                        ) {
                            TagSection(titleText = "Gêneros", tags = manga.tags)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = manga.description)
                        }
                    }
                }

                Chapters.index -> {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorScheme.background)
                                .padding(top = 8.dp)
                                .offset(y = -offset),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(
                                modifier = Modifier.clickable {
                                    viewModel.changeOrder(ChaptersOrder.Natural)
                                }
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 2.dp),
                                    text = "Crescente",
                                    color = if (chaptersState.order == ChaptersOrder.Natural)
                                        Color.White
                                    else
                                        Color.Gray,
                                    fontSize = 12.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Box(
                                modifier = Modifier.clickable {
                                    viewModel.changeOrder(
                                        ChaptersOrder.Reversed
                                    )
                                }
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 2.dp),
                                    text = "Decrescente",
                                    color = if (chaptersState.order == ChaptersOrder.Reversed)
                                        Color.White
                                    else
                                        Color.Gray,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                    chaptersList.forEach { (chapterNumber, chapterList) ->
                        val isExpanded = expandedChapters[chapterNumber] ?: false
                        item {
                            Column(modifier = Modifier.fillMaxWidth().offset(y = -offset)) {
                                HorizontalDivider()
                                ChaptersList(
                                    chapterNumber = chapterNumber,
                                    chapters = chapterList,
                                    isExpanded = isExpanded,
                                    onClick = { viewModel.expandChapter(chapterNumber) },
                                    readerNavigation = {
                                        navigator.navigate(ReaderScreenDestination(it))
                                    }
                                )
                            }
                        }
                    }

                    item {
                        Box(modifier = Modifier.fillMaxWidth().navigationBarsPadding())
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = isLoading || isError,
            enter = fadeIn(tween(300)),
            exit = fadeOut(tween(300)),
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                isLoading -> Loader(StateAnimationType.DETAILED_PAGES)

                isError -> {
                    ErrorScreen(
                        enabled = detailState.isError,
                        onBackPressed = { navigator.navigateUp() }
                    ) { viewModel.refresh(id) }
                }
            }
        }
    }
}