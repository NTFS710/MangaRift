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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.data.Manga
import com.sephirita.mangarift.ui.model.DetailsPageTab

@Composable
fun DetailPageTabs(
    modifier: Modifier = Modifier,
    item: Manga
) {
    var selectedTab by remember { mutableStateOf(DetailsPageTab.Details) }

    val error = false

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailsPageTab.entries.forEach {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(it.getCorners())
                            .background(
                                if (selectedTab == it)
                                    MaterialTheme.colorScheme.background
                                else
                                    MaterialTheme.colorScheme.surface
                            )
                            .weight(0.5f)
                            .clickable {
                                selectedTab = it
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            text = it.getText(),
                            color = if (selectedTab == it) White else Gray,
                            fontSize = 20.sp
                        )
                    }
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
                when (selectedTab) {
                    DetailsPageTab.Details -> DetailedInformation(item = item)
                    DetailsPageTab.Chapters -> ChaptersList(chapters = item.chapters)
                }
            }
        }
    }
}