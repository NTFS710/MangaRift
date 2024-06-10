package com.sephirita.mangarift.presentation.component.list.horizontal

import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.R
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.presentation.isFirstItemVisible
import com.sephirita.mangarift.presentation.isLastItemVisible
import com.sephirita.mangarift.presentation.theme.CharcoalBlack

@Composable
fun HorizontalMangaList(
    modifier: Modifier = Modifier,
    listTitle: String,
    items: List<Manga>,
    detailNavigation: (String) -> Unit,
    searchNavigation: () -> Unit
) {
    val screenSize = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenSize / 3
    val itemHeight = itemWidth + 60.dp

    val listState = rememberLazyListState()

    if (items.isNotEmpty()) {
        Box(modifier = modifier.clipToBounds()) {
            Column(
                modifier = Modifier.clipToBounds(),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = listTitle,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(onClick = searchNavigation),
                        painter = painterResource(id = R.drawable.ic_round_arrow_forward),
                        contentDescription = "Go to SearchScreen icon"
                    )
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxSize(),
                        state = listState,
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        itemsIndexed(
                            items = items,
                            itemContent = { _, item ->
                                HorizontalMangaListItem(
                                    item = item,
                                    itemWidth = itemWidth,
                                    itemHeight = itemHeight,
                                    onClick = { detailNavigation(it) }
                                )
                            }
                        )
                    }
                    val brushColors = listOf(CharcoalBlack.copy(alpha = 0.85F), Color.Transparent)
                    androidx.compose.animation.AnimatedVisibility(
                        visible = !listState.isFirstItemVisible,
                        enter = expandHorizontally(),
                        exit = shrinkHorizontally(),
                    ) {
                        val brush = Brush.horizontalGradient(
                            colors = brushColors
                        )
                        Box(
                            modifier = Modifier
                            .width(50.dp)
                            .height(itemHeight)
                            .background(brush)
                        )
                    }
                    androidx.compose.animation.AnimatedVisibility(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        visible = !listState.isLastItemVisible,
                        enter = expandHorizontally(expandFrom = Alignment.Start),
                        exit = shrinkHorizontally(),
                    ) {
                        val brush = Brush.horizontalGradient(
                            colors = brushColors,
                            startX = Float.POSITIVE_INFINITY,
                            endX = 0.0f
                        )
                        Box(
                            modifier = Modifier
                            .width(50.dp)
                            .height(itemHeight)
                            .background(brush)
                        )
                    }
                }
            }
        }
    }
}