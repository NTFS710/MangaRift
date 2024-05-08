package com.sephirita.mangarift.ui.components.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.ui.components.sohprateste.Chapter

@Composable
fun ChaptersList(
    modifier: Modifier = Modifier,
    chaptersList: Map<Float, List<Chapter>>,
    expandedChapterList: Map<Float, Boolean>,
    expandChapterCallback: (Float) -> Unit,
    readerNavigation: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        Column {
            chaptersList.forEach { (chapterNumber, chapterList) ->
                val isExpanded = expandedChapterList[chapterNumber] ?: false
                HorizontalDivider()
                ChapterListItem(
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