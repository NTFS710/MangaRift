package com.sephirita.mangarift.ui.components.reader

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReaderChapter(chapterId: String) {
    val viewModel: ReaderViewModel = koinViewModel()
    val chapterPages by viewModel.chapterPages.collectAsState()

    val context = LocalContext.current
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var showDialog by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(pageCount = { chapterPages.size })

    LaunchedEffect(key1 = Unit) {
        viewModel.getChapterToRead(chapterId)
    }

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


    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .combinedClickable(
                onClick = {},
                onLongClick = { showDialog = true },
                interactionSource = null,
                indication = null
            )
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
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { /*TODO*/ }
                        ) {
                            Row {
//                                Icon(painter = , contentDescription = )
                                Text(text = "Padrão")
                            }
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { /*TODO*/ }
                        ) {
                            Row {
//                                Icon(painter = , contentDescription = )
                                Text(text = "Webtoon")
                            }
                        }
                    }
                }
            }
        }

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
            val currentItem = chapterPages[it]
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .transformable(state = transformableState, canPan = { scale != 1f }),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                model = currentItem,
                contentDescription = "PDF Image"
            )
        }
    }
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}