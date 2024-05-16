package com.sephirita.mangarift.ui.screen.reader

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.sephirita.mangarift.ui.components.reader.ReaderChapter

@Destination
@Composable
fun ReaderScreen(chapterId: String) {
    ReaderChapter(chapterId)
}