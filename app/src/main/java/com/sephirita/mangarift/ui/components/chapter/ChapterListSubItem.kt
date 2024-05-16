package com.sephirita.mangarift.ui.components.chapter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.R
import com.sephirita.mangarift.domain.model.Chapter
import com.sephirita.mangarift.ui.model.Language.Companion.EN
import com.sephirita.mangarift.ui.model.Language.Companion.PT_BR
import com.sephirita.mangarift.domain.formatChapterNumber

@Composable
fun ChapterListSubItem(
    modifier: Modifier = Modifier,
    chapter: Chapter,
    readerNavigation: () -> Unit
) {
    val flagIcon = when (chapter.translatedLanguage) {
        PT_BR -> R.drawable.ic_br_flag
        EN -> R.drawable.ic_en_flag
        else -> R.drawable.ic_unk_flag
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = readerNavigation)
    ) {
        Column(
            modifier = Modifier.padding(
                start = 32.dp,
                top = 4.dp,
                bottom = 4.dp
            ),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = flagIcon),
                    contentDescription = "Language flag"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Ch. ${chapter.chapter.formatChapterNumber()} ${chapter.title}")
            }
            if (chapter.scan.isNotBlank()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.ic_scan),
                        contentDescription = "Language flag"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = chapter.scan, fontSize = 14.sp, lineHeight = 14.sp)
                }
            }
        }
    }
}