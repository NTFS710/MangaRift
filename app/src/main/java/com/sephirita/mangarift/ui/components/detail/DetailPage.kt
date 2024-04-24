package com.sephirita.mangarift.ui.components.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sephirita.mangarift.R
import com.sephirita.mangarift.data.Manga
import com.sephirita.mangarift.ui.components.header.Header
import com.sephirita.mangarift.ui.components.rating.RatingBar
import com.sephirita.mangarift.ui.components.text.StrokedText
import com.sephirita.mangarift.utils.formatChapterNumber
import com.sephirita.mangarift.utils.toDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailPage(
    modifier: Modifier = Modifier,
    item: Manga
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val backGroundHeight = (screenHeight / 10) * 4

    Scaffold(
        topBar = {
            Header()
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(bottom = 8.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                HorizontalDivider()
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 52.dp),
                    shape = (RoundedCornerShape(25f)),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Ler agora")
                }
            }
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .navigationBarsPadding()
                .padding(bottom = 80.dp),
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
                    modifier = Modifier
                        .height(backGroundHeight)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    StrokedText(text = item.title)
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
                                        .background(MaterialTheme.colorScheme.surface)
                                        .clickable { },
                                    contentAlignment = Center
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        textAlign = TextAlign.Center,
                                        text = "Capítulos",
                                        color = Gray,
                                        fontSize = 20.sp
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Box {
                                        Text(text = "Crescente", color = White, fontSize = 12.sp)
                                    }
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Box {
                                        Text(text = "Decrescente", color = Gray, fontSize = 12.sp)
                                    }
                                }
                                HorizontalDivider()
                                item.chapters.forEachIndexed { index, chapter ->
                                    Box(
                                        modifier = Modifier.padding(
                                            start = 16.dp,
                                            top = 4.dp,
                                            bottom = 4.dp
                                        ),
                                        contentAlignment = Center
                                    ) {
                                        Column {
                                            Row(
                                                verticalAlignment = Alignment.Bottom
                                            ) {
                                                Text(text = "${index + 1}.")
                                                Text(
                                                    text = "Capítulo ${chapter.chapter.formatChapterNumber()}"
                                                )
                                            }
                                            Box(
                                                modifier = Modifier.padding(start = 16.dp)
                                            ) {
                                                Text(
                                                    text = chapter.updatedAt.toDate(),
                                                    fontSize = 10.sp
                                                )
                                            }
                                        }
                                    }
                                    HorizontalDivider()
                                }
                            }
                        }
//                        Box(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(horizontal = 16.dp)
//                        ) {
//                            Column {
//
//                                Spacer(modifier = Modifier.height(8.dp))
//                                Text(
//                                    text = "Gêneros",
//                                    fontSize = 16.sp,
//                                    fontWeight = FontWeight.Bold
//                                )
//                                Spacer(modifier = Modifier.height(8.dp))
//                                FlowRow(
//                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
//                                    verticalArrangement = Arrangement.spacedBy(6.dp),
//                                    maxLines = 2
//                                ) {
//                                    item.tags.forEach {
//                                        // adicionar clicks na tag, pra abrir a tela de pesquisa usando a tag como fitro
//                                        Tag(
//                                            name = it.type,
//                                            background = MaterialTheme.colorScheme.surface
//                                        )
//                                    }
//                                }
//                                Spacer(modifier = Modifier.height(8.dp))
//                                Text(
//                                    text = item.description,
//                                    fontSize = 16.sp
//                                )
//                            }
//                        }
                    }
                }
            }
        }
    }
}