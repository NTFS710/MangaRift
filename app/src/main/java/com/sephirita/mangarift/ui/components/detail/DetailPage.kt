package com.sephirita.mangarift.ui.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sephirita.mangarift.data.Manga
import com.sephirita.mangarift.ui.components.rating.RatingBar
import com.sephirita.mangarift.ui.components.text.StrokedText

@Composable
fun DetailPage(
    modifier: Modifier = Modifier,
    item: Manga
) {
    // Header com seta de voltar e botão de download
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val backGroundHeight = (screenHeight / 10) * 4

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth,
            model = item.image,
            contentDescription = "Background Detail Image"
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .height(backGroundHeight)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(top = 40.dp),
                        fontSize = 24.sp,
                        text = "seta",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {  }
                    ) {
                        //Text with stroke border

                        StrokedText(
                            text = item.title,
//                            textColor = MaterialTheme.colorScheme.secondary.toArgb(),
                            size = 54.sp.value,
//                            textStrokeColor = Black.toArgb(),
//                            textStrokeWidth = 3f
                        )

                        val offset = Offset(0f, 10.0f)
                        Text(
                            text = "Hello world!",
                            style = TextStyle(
                                fontSize = 20.sp,
                                shadow = Shadow(
                                    color = Black, offset = offset, blurRadius = 3f
                                )
                            )
                        )

                    }
                }
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .clickable { }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = CenterVertically
                ) {
                    RatingBar(
                        rating = item.rating.toDouble(),
                        starSize = 26.dp
                    )
                    Text(text = item.rating, fontSize = 20.sp)
                }
            }
        }
    }
}