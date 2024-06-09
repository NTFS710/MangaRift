package com.sephirita.mangarift.presentation.component.list.horizontal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                    var showBrush by remember { mutableStateOf(false) }
                    LazyRow(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        itemsIndexed(
                            items = items,
                            itemContent = { index, item ->
                                showBrush = (index > 0)
                                HorizontalMangaListItem(
                                    item = item,
                                    itemWidth = itemWidth,
                                    itemHeight = itemHeight,
                                    onClick = { detailNavigation(it) }
                                )
                            }
                        )
                    }
                    if (showBrush) {
                        val brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.85F),
                                Color.Transparent
                            )
                        )
                        Box(modifier = Modifier.width(50.dp).height(itemHeight).background(brush))
                    }
                }
            }
        }
    }
}