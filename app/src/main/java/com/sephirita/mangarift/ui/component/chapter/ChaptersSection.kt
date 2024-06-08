package com.sephirita.mangarift.ui.component.chapter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.ui.model.ChaptersOrder
import com.sephirita.mangarift.ui.model.ChaptersOrder.Natural
import com.sephirita.mangarift.ui.model.ChaptersOrder.Reversed
import com.sephirita.mangarift.ui.model.FormatedChapters

@Composable
fun ChaptersSection(
    modifier: Modifier = Modifier,
    chapters: FormatedChapters,
    changeChaptersOrder: (ChaptersOrder) -> Unit,
    expandedChapterList: Map<Float, Boolean>,
    expandChapterCallback: (Float) -> Unit,
    readerNavigation: (String) -> Unit
) {
    val chaptersList = if (chapters.order == Natural) chapters.natural else chapters.reversed

    Box(
        modifier = modifier.fillMaxSize().padding(top = 8.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier.clickable { changeChaptersOrder(Natural) }
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 2.dp),
                        text = "Crescente",
                        color = if (chapters.order == Natural)
                            Color.White
                        else
                            Color.Gray,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier.clickable { changeChaptersOrder(Reversed) }
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 2.dp),
                        text = "Decrescente",
                        color = if (chapters.order == Reversed)
                            Color.White
                        else
                            Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            chaptersList.forEach { (chapterNumber, chapterList) ->
                val isExpanded = expandedChapterList[chapterNumber] ?: false
                HorizontalDivider()
                ChaptersList(
                    chapterNumber = chapterNumber,
                    chapters = chapterList,
                    isExpanded = isExpanded,
                    onClick = { expandChapterCallback(chapterNumber) },
                    readerNavigation = readerNavigation
                )
            }
        }
    }
}