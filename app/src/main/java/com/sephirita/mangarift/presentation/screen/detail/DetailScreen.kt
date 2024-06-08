package com.sephirita.mangarift.presentation.screen.detail

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.presentation.component.detail.DetailsPager
import com.sephirita.mangarift.presentation.component.load.Loader
import com.sephirita.mangarift.presentation.component.scaffold.ScrollableScreenContainer
import com.sephirita.mangarift.presentation.component.text.StrokedText
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

    LaunchedEffect(key1 = Unit) {
        viewModel.getMangaDetails(id)
    }

    with(detailState) {
        ScrollableScreenContainer(
            onNavigateBack = { navigator.navigateUp() },
            imageUri = manga.image,
            mangaTitle = {
                StrokedText(
                    text = manga.title,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            },
            bottomBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .navigationBarsPadding()
                ) {
                    HorizontalDivider()
                }
            }
        ) {
            DetailsPager(
                tags = manga.tags,
                description = manga.description,
                chapters = chaptersState,
                changeChaptersOrder = { viewModel.changeOrder(it) },
                expandedChapterList = expandedChapter,
                expandChapterCallback = { viewModel.expandChapter(it) },
                readerNavigation = {
                    navigator.navigate(
                        ReaderScreenDestination(
                            it
                        )
                    )
                }
            )
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