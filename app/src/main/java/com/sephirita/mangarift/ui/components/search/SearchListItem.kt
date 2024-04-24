package com.sephirita.mangarift.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sephirita.mangarift.data.Manga
import com.sephirita.mangarift.ui.components.card.Tag
import com.sephirita.mangarift.ui.components.rating.RatingBar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchListItem(item: Manga) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val itemHeight = screenHeight / 6

    Row(
        modifier = Modifier
            .height(itemHeight)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillHeight,
            model = item.image,
            contentDescription = "Background Banner Image"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = item.title)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                maxLines = 2
            ) {
                item.tags.forEach {
                    Tag(name = it.type)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(
                    rating = item.rating.toDouble(),
                    stars = 1,
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    color = Color.White,
                    text = item.rating,
                    fontSize = 14.sp
                )
            }
        }
    }
}