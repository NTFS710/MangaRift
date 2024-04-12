package com.sephirita.mangarift.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import coil.compose.rememberImagePainter
import com.sephirita.mangarift.data.Manga
import com.sephirita.mangarift.ui.components.sohprateste.Tag

@Composable
fun StandardMangaDisplay(
    modifier: Modifier = Modifier,
    item: Manga
) {
    val screenSize = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenSize / 3
    val itemHeight = itemWidth + 60.dp
    val painter = rememberImagePainter(data = item.image)
    Box(
        modifier = modifier
            .size(width = itemWidth, height = itemHeight)
            .clickable {
                println("F total")
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(4.dp))
                    .weight(0.85f),
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth,
                painter = painter,
                contentDescription = "Manga Cover Image"
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

fun getMockedManga() = Manga(
    id = "dd0b8df1-1b59-4ce3-a6f3-cc19cf6ca4f6",
    author = "Himiya Jouzu",
    image = "https://mangadex.org/covers/dd0b8df1-1b59-4ce3-a6f3-cc19cf6ca4f6/334123d5-8f81-479e-a3b4-0ec9eb29bf5e.jpg",
    rating = "8.20",
    tags = getMangaTags(),
    title = "Iinchou Desu ga Furyou ni Naru Hodo Koi Shitemasu!"
)

fun getMangaTags(): List<Tag> {
    var tags: MutableList<Tag> = mutableListOf()
    tags.add(
        Tag(
            id = "1",
            type = "Suggestive",
            attributes = null,
            relationships = null
        )
    )
    tags.add(
        Tag(
            id = "2",
            type = "Comedy",
            attributes = null,
            relationships = null
        )
    )
    tags.add(
        Tag(
            id = "3",
            type = "Romance",
            attributes = null,
            relationships = null
        )
    )
    tags.add(
        Tag(
            id = "4",
            type = "Slice of Life",
            attributes = null,
            relationships = null
        )
    )
    tags.add(
        Tag(
            id = "5",
            type = "School Life",
            attributes = null,
            relationships = null
        )
    )
    return tags
}