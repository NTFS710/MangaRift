package com.sephirita.mangarift.ui.component.list.horizontal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.ui.component.load.Loader
import com.sephirita.mangarift.ui.model.StateAnimationType

@Composable
fun HorizontalMangaListItem(
    modifier: Modifier = Modifier,
    item: Manga,
    onClick: (String) -> Unit
) {
    val screenSize = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenSize / 3
    val itemHeight = itemWidth + 60.dp
    Box(
        modifier = modifier
            .size(width = itemWidth, height = itemHeight)
            .clickable{ onClick(item.id) },
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp))
                    .weight(0.85f),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
                model = item.image,
                contentDescription = "Manga Cover Image",
                loading = {
                    Loader(loadingAnimationType = StateAnimationType.DETAILED_PAGES)
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.weight(0.15f),
                text = item.title,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}