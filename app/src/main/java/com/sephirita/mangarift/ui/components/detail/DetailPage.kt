package com.sephirita.mangarift.ui.components.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sephirita.mangarift.R
import com.sephirita.mangarift.ui.components.detail.viewmodel.DetailViewModel
import com.sephirita.mangarift.ui.components.header.Header
import com.sephirita.mangarift.ui.components.rating.RatingBar
import com.sephirita.mangarift.ui.components.text.StrokedText
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailPage(
    id: String
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val backgroundHeight = (screenHeight / 10) * 4
    val corner = 24.dp

    val viewModel: DetailViewModel = koinViewModel()
    val state by viewModel.detailState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.getMangaDetails(id)
    }

    with (state) {
        if (isLoading) {
            println("carrega")
        } else if (isError) {
            println("deu errado")
        } else {
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
                    }
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .navigationBarsPadding()
                        .padding(bottom = 10.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    AsyncImage(
                        modifier = Modifier.height(backgroundHeight+corner),
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.FillWidth,
                        model = manga.image,
                        contentDescription = "Background Detail Image"
                    )
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .height(backgroundHeight)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            StrokedText(text = manga.title)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(
                                    shape = RoundedCornerShape(
                                        topStart = corner,
                                        topEnd = corner
                                    )
                                )
                                .background(MaterialTheme.colorScheme.background)
                                .padding(top = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
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
                                            rating = manga.rating.toDouble(), starSize = 26.dp
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(text = manga.rating, fontSize = 20.sp)
                                    }
                                    Icon(
                                        modifier = Modifier
                                            .size(24.dp, 24.dp)
                                            .clickable { },
                                        contentDescription = "Bookmark",
                                        painter = painterResource(id = R.drawable.ic_outline_bookmark) // Adicionar ícone pintado pra mostrar ao usuário que o mangá está na lista de favoritos dele
                                    )
                                }
                                HorizontalDivider(color = Gray)
                                DetailPageTabs(
                                    tags = manga.tags,
                                    description = manga.description,
                                    chaptersList = viewModel.getChapters(),
                                    expandedChapterList = expandedChapter,
                                    expandChapterCallback = { viewModel.expandChapter(it) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}