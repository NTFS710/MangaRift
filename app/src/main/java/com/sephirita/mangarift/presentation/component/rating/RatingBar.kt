package com.sephirita.mangarift.presentation.component.rating

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.R

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 10,
    starColor: Color = Color.Yellow,
    starSize: Dp = 24.dp
) {
    var isHalfStar = (rating % 1) > 0.0
    Row {
        for (index in 1..stars) {
            val image = if (index <= rating) {
                R.drawable.ic_round_star
            } else if (isHalfStar) {
                isHalfStar = false
                R.drawable.ic_round_star_half
            } else {
                R.drawable.ic_round_star_outline
            }
            Icon(
                modifier = modifier.size(starSize, starSize),
                contentDescription = "Estrela",
                tint = starColor,
                painter = painterResource(id = image)
            )
        }
    }
}