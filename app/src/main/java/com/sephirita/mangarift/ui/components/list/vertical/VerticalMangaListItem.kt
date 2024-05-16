package com.sephirita.mangarift.ui.components.list.vertical

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.ui.components.card.TagSection
import com.sephirita.mangarift.ui.components.rating.RatingBar

@Composable
fun VerticalMangaListItem(
    item: Manga,
    onClick: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val itemHeight = screenHeight / 6
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imageWidth = (screenWidth / 100) * 25

    Row(
        modifier = Modifier
            .height(itemHeight)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .width(imageWidth),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
            model = item.image,
            contentDescription = "Background Banner Image"
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 4.dp, start = 6.dp, end = 6.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TagSection(
                titleText = item.title,
                titleSize = 14.sp,
                tags = item.tags,
                tagsMaxLines = 1,
                titleWeight = null,
                titleHeight = 14.sp * 1.2,
                spacerHeight = 2.dp,
                tagBackground = MaterialTheme.colorScheme.background
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                RatingBar(
                    rating = item.rating.toDouble(),
                    stars = 1,
                    starSize = 22.dp
                )
                Text(
                    color = Color.White,
                    text = item.rating,
                    fontSize = 14.sp
                )
            }
        }
    }
}