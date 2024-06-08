package com.sephirita.mangarift.presentation.screen.reader

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.compose.SubcomposeAsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.presentation.component.dialog.ReadingStyleDialog
import com.sephirita.mangarift.presentation.screen.error.ErrorScreen
import com.sephirita.mangarift.presentation.component.load.Loader
import com.sephirita.mangarift.presentation.model.StateAnimationType
import com.sephirita.mangarift.presentation.screen.reader.viewmodel.ReaderViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun ReaderScreen(
    chapterId: String,
    navigator: DestinationsNavigator
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var showDialog by remember { mutableStateOf(false) }
    var horizontalReading by remember { mutableStateOf(true) }

    val viewModel: ReaderViewModel = koinViewModel()
    val state by viewModel.readerState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { state.pages.size })

    LaunchedEffect(key1 = Unit) {
        viewModel.getChapterToRead(chapterId)
    }

    HideSystemBars()

    with(state) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { showDialog = true },
                    interactionSource = null,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            val transformableState =
                rememberTransformableState { zoomChange, offsetChange, rotationChange ->
                    scale = (scale * zoomChange).coerceIn(1f, 5f)
                    val extraWidth = (scale - 1) * constraints.maxWidth
                    val extraHeight = (scale - 1) * constraints.maxHeight
                    val maxX = extraWidth / 2
                    val maxY = extraHeight / 2
                    offset = Offset(
                        x = (offset.x + (scale * offsetChange.x)).coerceIn(-maxX, maxX),
                        y = (offset.y + (scale * offsetChange.y)).coerceIn(-maxY, maxY)
                    )
                }

            if (showDialog) {
                ReadingStyleDialog(
                    changeDialogVisibility = { showDialog = it },
                    changeReadingStyle = { horizontalReading = it }
                )
            }

            if (horizontalReading) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offset.x,
                            translationY = offset.y
                        ),
                    beyondViewportPageCount = 3
                ) {
                    val currentItem = state.pages[it]
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .transformable(
                                state = transformableState,
                                canPan = { scale != 1f }),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center,
                        model = currentItem,
                        contentDescription = "Manga Page",
                        loading = {
                            Loader(loadingAnimationType = StateAnimationType.DETAILED_PAGES)
                        }
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offset.x,
                            translationY = offset.y
                        )
                        .transformable(
                            state = transformableState,
                            canPan = { scale != 1f })
                        .verticalScroll(rememberScrollState()),
                ) {
                    state.pages.forEachIndexed { index, it ->
                        val currentItem = state.pages[index]
                        SubcomposeAsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit, //
                            alignment = Alignment.Center,
                            model = currentItem,
                            contentDescription = "Manga Page",
                            loading = {
                                Loader(loadingAnimationType = StateAnimationType.DETAILED_PAGES)
                            }
                        )
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
                isLoading -> Loader(loadingAnimationType = StateAnimationType.NONE)

                isError -> {
                    ErrorScreen(
                        enabled = state.isError,
                        onBackPressed = { navigator.navigateUp() }
                    ) { viewModel.getChapterToRead(chapterId) }
                }
            }
        }
    }
}

@Composable
fun HideSystemBars() {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val window = context.findActivity()?.window ?: return@DisposableEffect onDispose {}
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        onDispose {
            insetsController.apply {
                show(WindowInsetsCompat.Type.statusBars())
                show(WindowInsetsCompat.Type.navigationBars())
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            }
        }
    }
}

private fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}