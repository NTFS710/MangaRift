package com.sephirita.mangarift.ui.component.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.sephirita.mangarift.domain.model.Manga
import com.sephirita.mangarift.ui.component.card.TagSection
import com.sephirita.mangarift.ui.component.load.Loader
import com.sephirita.mangarift.ui.model.StateAnimationType
import com.sephirita.mangarift.ui.theme.TransparentGray

@Composable
fun Banner(
    item: Manga,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
            model = item.image,
            contentDescription = "Background Banner Image",
            loading = {
                Loader(loadingAnimationType = StateAnimationType.DETAILED_PAGES)
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(TransparentGray)
                .padding(start = 10.dp, end = 10.dp, top = 50.dp)
        ) {
            Column {
                Row(modifier = Modifier.weight(0.9f)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.5f)
                    ) {
                        SubcomposeAsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop,
                            model = item.image,
                            contentDescription = "Background Banner Image",
                            loading = {
                                Loader(loadingAnimationType = StateAnimationType.DETAILED_PAGES)
                            }
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(start = 8.dp, end = 32.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = item.title, maxLines = 8, overflow = TextOverflow.Ellipsis)
                            Text(text = item.author)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                TagSection(tags = item.tags, tagsMaxLines = 1)
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}