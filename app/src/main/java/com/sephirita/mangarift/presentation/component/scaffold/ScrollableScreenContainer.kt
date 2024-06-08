package com.sephirita.mangarift.presentation.component.scaffold

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sephirita.mangarift.R


internal const val IMAGE_HEIGHT = 424
internal const val PARALLAX_COEFFICIENT = 0.75F
internal const val CORNER = 24

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScrollableScreenContainer(
    onNavigateBack: () -> Unit,
    imageUri: String?,
    mangaTitle: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    scrollableContent: @Composable () -> Unit
) {
    Scaffold(bottomBar = bottomBar) {
        val lazyListState = rememberLazyListState()
        val cornerSizeInPx = with(LocalDensity.current) { CORNER.dp.toPx() }

        fun getCollapsingProgress(): Float {
            val visibleItemsInfo = lazyListState.layoutInfo.visibleItemsInfo
            val imageInfo = visibleItemsInfo.find { it.index == 0 }
            val imageSize = imageInfo?.size?.minus(cornerSizeInPx)
            val imageOffset = imageInfo?.offset?.toFloat() ?: 0F

            var progress = (imageSize?.plus(imageOffset))?.div(imageSize)
            if (progress == null || progress < 0) progress = 0F

            return progress
        }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Image(
                        imageUri = imageUri,
                        mangaTitle = mangaTitle,
                        lazyListState = lazyListState,
                        getCollapsingProgress = { getCollapsingProgress() },
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .offset(y = -(CORNER.dp))
                            .graphicsLayer {
                                val progress = getCollapsingProgress()
                                val animatedCorner = CORNER - (CORNER - (CORNER * progress))

                                clip = true

                                shape = RoundedCornerShape(
                                    topStart = animatedCorner.dp,
                                    topEnd = animatedCorner.dp
                                )
                            }
                    ) { scrollableContent() }
                }
            }

            ToolbarContainer(
                getCollapsingProgress = { getCollapsingProgress() },
                onNavigateBack = onNavigateBack
            )
        }
    }

    BackHandler { onNavigateBack() }
}

@Composable
internal fun Image(
    imageUri: String?,
    mangaTitle: @Composable () -> Unit,
    lazyListState: LazyListState,
    getCollapsingProgress: () -> Float
) {
    var scrolledY = 0f
    var previousOffset = 0

    Box(modifier = Modifier.fillMaxWidth()) {
        val imageModifier = Modifier
            .height(IMAGE_HEIGHT.dp)
            .fillMaxWidth()
            .graphicsLayer {
                scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                translationY = scrolledY * PARALLAX_COEFFICIENT
                previousOffset = lazyListState.firstVisibleItemScrollOffset
                alpha = getCollapsingProgress()
            }

        AsyncImage(
            model = imageUri,
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = -CORNER.dp)
                .align(Alignment.BottomStart)
        ){
            mangaTitle()
        }
    }
}


@Composable
fun ToolbarContainer(
    getCollapsingProgress: () -> Float,
    onNavigateBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        ToolbarShadow(getCollapsingProgress)
        ToolbarBackground(getCollapsingProgress)
        ToolbarIcons(onNavigateBack)
    }
}

@Composable
private fun ToolbarShadow(getCollapsingProgress: () -> Float) {
    val shadowBrush = Brush.verticalGradient(
        colors = listOf(
            Color.Black.copy(alpha = 0.85F),
            Color.Transparent
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .graphicsLayer {
                alpha = getCollapsingProgress()
            }
            .background(shadowBrush)
    )
}

@Composable
private fun BoxScope.ToolbarBackground(getCollapsingProgress: () -> Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                val progress = getCollapsingProgress()
                alpha = when {
                    progress <= 0.1F -> 1F - progress
                    progress <= 0.15F -> 0.9F - progress
                    progress <= 0.2F -> 0.8F - progress
                    progress <= 0.25F -> 0.7F - progress
                    progress <= 0.3F -> 0.6F - progress
                    progress <= 0.35F -> 0.5F - progress
                    progress <= 0.4F -> 0.4F - progress
                    progress <= 0.45F -> 0.3F - progress
                    progress <= 0.5F -> 0.2F - progress
                    progress <= 0.55F -> 0.1F - progress
                    progress <= 0.6F -> 0.05F - progress
                    else -> 0F
                }
            }
            .background(colorScheme.background)
            .statusBarsPadding()
            .padding(top = 52.dp)
            .align(Alignment.TopCenter)
    ) {
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = colorScheme.onBackground.copy(alpha = 0.3F)
        )
    }
}

@Composable
private fun BoxScope.ToolbarIcons(
    onNavigateBack: () -> Unit
) {
    val iconBackgroundAlpha = 0.8F

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .align(Alignment.TopCenter)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(iconBackgroundAlpha))
                .clickable(onClick = onNavigateBack)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_round_arrow_back),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(18.dp)
                    .offset(x = -(1.dp))
            )
        }
    }
}