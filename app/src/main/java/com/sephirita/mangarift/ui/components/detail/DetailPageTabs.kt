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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.utils.getMangaChapters

@Composable
fun DetailPageTabs(
    modifier: Modifier = Modifier
) {
    val tabWeight = 0.5f
    val tabHeight = 50.dp
    val selectedTabBackgroundColor = MaterialTheme.colorScheme.background
    val unselectedTabBackgroundColor = MaterialTheme.colorScheme.surface
    val selectedTabTextColor = White
    val unselectedTabTextColor = Gray

    val error = false

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .height(tabHeight)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(bottomEnd = 8.dp))
                        .background(unselectedTabBackgroundColor)
                        .weight(tabWeight)
                        .clickable {
//                    muda cor do background e mostrar as coisas da aba de detalhes
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        text = "Detalhes",
                        color = unselectedTabTextColor,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(bottomStart = 8.dp))
                        .background(selectedTabBackgroundColor)
                        .weight(tabWeight)
                        .clickable {
//                    muda cor do background e mostrar a lista de capítulos
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        text = "Capítulos",
                        color = selectedTabTextColor,
                        fontSize = 20.sp
                    )
                }
            }
            if (error) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Erro, nenhum capítulo encontrado",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    )
                }
            } else {
                ChaptersList(chapters = getMangaChapters())
            }
        }
    }
}