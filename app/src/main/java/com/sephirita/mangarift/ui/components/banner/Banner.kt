package com.sephirita.mangarift.ui.components.banner

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sephirita.mangarift.data.Manga
import com.sephirita.mangarift.ui.components.card.Tag
import com.sephirita.mangarift.ui.theme.TransparentGray

@Composable
fun Banner(item: Manga, itemWidth: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { }
    ) {

        AsyncImage(
            modifier = Modifier.width(itemWidth),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth,
            model = item.image,
            contentDescription = "Background Banner Image"
        )

        Box(
            modifier = Modifier
                .width(itemWidth)
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
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            alignment = Alignment.CenterStart,
                            contentScale = ContentScale.Fit,
                            model = item.image,
                            contentDescription = "Background Banner Image"
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(start = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = item.title)
                            Text(text = item.author)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    item.tags.takeLast(5).forEach {
                        Tag(name = it.type)
                    }
                }
            }
        }
    }
}