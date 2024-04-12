package com.sephirita.mangarift.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.ui.theme.CharcoalGray

@Composable
fun Tag(name: String) {
    val background = MaterialTheme.colorScheme.background
    Text(
        name,
        fontSize = 12.sp,
        color = White,
        modifier = Modifier
            .drawBehind {
                drawRoundRect(
                    background,
                    cornerRadius = CornerRadius(100f, 100f)
                )
            }
            .padding(horizontal = 6.dp, vertical = 2.dp)
    )
}