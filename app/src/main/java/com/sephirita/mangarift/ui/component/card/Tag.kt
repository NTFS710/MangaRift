package com.sephirita.mangarift.ui.component.card

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tag(
    name: String,
    background: Color = MaterialTheme.colorScheme.background,
    cornerRadius: Float = 100f
) {
    Text(
        name,
        fontSize = 12.sp,
        color = White,
        modifier = Modifier
            .drawBehind {
                drawRoundRect(
                    background,
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                )
            }
            .padding(horizontal = 10.dp, vertical = 2.dp)
    )
}