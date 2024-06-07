package com.sephirita.mangarift.ui.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.R

@Composable
fun DetailHeader(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.8f),
                        Color.Black.copy(alpha = 0.6f),
                        Color.Transparent
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
            .statusBarsPadding()
            .padding(start = 16.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .clickable(onClick = onBackPressed),
            painter = painterResource(id = R.drawable.ic_round_arrow_back),
            contentDescription = "Icone para voltar na navegação"
        )
    }
}