package com.sephirita.mangarift.ui.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.R
import com.sephirita.mangarift.ui.components.sohprateste.Chapter
import com.sephirita.mangarift.utils.formatChapterNumber

@Composable
fun ChapterListSubItem(
    modifier: Modifier = Modifier,
    chapter: Chapter,
    readerNavigation: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = readerNavigation )
    ) {
        Row(
            modifier = Modifier.padding(
                start = 32.dp,
                top = 6.dp,
                bottom = 6.dp
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_br_flag), // muda de acordo com a imagem
                contentDescription = "Language flag"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Ch. ${chapter.chapter.formatChapterNumber()} - ${chapter.title}")
        }
    }
}