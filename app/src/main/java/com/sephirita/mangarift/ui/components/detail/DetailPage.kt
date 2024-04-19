package com.sephirita.mangarift.ui.components.detail

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sephirita.mangarift.R
import com.sephirita.mangarift.data.Manga
import com.sephirita.mangarift.ui.components.card.Tag
import com.sephirita.mangarift.ui.components.rating.RatingBar
import com.sephirita.mangarift.ui.components.text.StrokedText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailPage(
    modifier: Modifier = Modifier,
    item: Manga
) {
    // Header com seta de voltar e botão de download
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val backGroundHeight = (screenHeight / 10) * 4

    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
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
                        .padding(horizontal = 16.dp), verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(top = 30.dp),
                        fontSize = 24.sp,
                        text = "seta",
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                    StrokedText(text = item.title)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(verticalAlignment = CenterVertically) {
                            RatingBar(
                                rating = item.rating.toDouble(), starSize = 26.dp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = item.rating, fontSize = 20.sp)
                        }
                        Icon(
                            modifier = modifier
                                .size(24.dp, 24.dp)
                                .clickable { },
                            contentDescription = "Bookmark",
                            painter = painterResource(id = R.drawable.ic_outline_bookmark) // Adicionar ícone pintado pra mostrarr ao usuário que o mangá está na lista de favoritos dele
                        )
                    }
                    HorizontalDivider(color = Gray)
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(), contentAlignment = Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(0.5f)
                                    .clip(RoundedCornerShape(bottomEnd = 8.dp))
                                    .clickable { },
                                contentAlignment = Center
                            ) {
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "Detalhes",
                                    fontSize = 20.sp
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(0.5f)
                                    .clip(RoundedCornerShape(bottomStart = 8.dp))
                                    .clickable { },
                                contentAlignment = Center
                            ) {
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "Capítulos",
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        Column {

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Gêneros",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp),
                                maxLines = 2
                            ) {
                                item.tags.forEach {
                                    Tag(
                                        name = it.type,
                                        background = MaterialTheme.colorScheme.surface
                                    )
                                }
                            }
                        }
                        
                    }
                }
            }
        }
    }
}