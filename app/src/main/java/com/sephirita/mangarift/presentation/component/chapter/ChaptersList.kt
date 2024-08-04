package com.sephirita.mangarift.presentation.component.chapter

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.domain.formatChapterNumber
import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.domain.toDate

@Composable
fun ChaptersList(
    modifier: Modifier = Modifier,
    chapterNumber: Float,
    chapters: List<Chapter>,
    isExpanded: Boolean,
    onClick: () -> Unit,
    readerNavigation: (String) -> Unit
) {
    val paddingBottom = if (isExpanded) 0.dp else 4.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colorScheme.background)
            .animateContentSize()
            .clickable(onClick = onClick),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier.padding(top = 4.dp, bottom = paddingBottom)
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Capítulo ${chapterNumber.formatChapterNumber()}"
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = chapters.first().updatedAt.toDate(),
                fontSize = 10.sp
            )
            if (isExpanded) {
                chapters.forEach {
                    HorizontalDivider()
                    ChaptersListItem(
                        chapter = it,
                        readerNavigation = { readerNavigation(it.id) }
                    )
                }
            }
        }
    }
}