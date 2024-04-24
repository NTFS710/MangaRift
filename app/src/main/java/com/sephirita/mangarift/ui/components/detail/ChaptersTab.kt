package com.sephirita.mangarift.ui.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChaptersTab(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(bottomStart = 8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = "Capítulos",
            color = Color.Gray,
            fontSize = 20.sp
        )
    }

}