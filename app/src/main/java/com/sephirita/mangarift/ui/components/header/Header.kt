package com.sephirita.mangarift.ui.components.header

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

//    Ideia de Header
//
//    Possui itens que devem ou não ser habilitados, dependendo de que classe carrega eles
//
//    1 : Botão de voltar
//    2 : Campo de busca
//    3 : Botão de DFo

@Composable
fun Header(
    modifier: Modifier = Modifier
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
                .clickable { },
            painter = painterResource(id = R.drawable.ic_round_arrow_back),
            contentDescription = "Icone para voltar na navegação"
        )
        //Adicionar Icon de download
    }
}