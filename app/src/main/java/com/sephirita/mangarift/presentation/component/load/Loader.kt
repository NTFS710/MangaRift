package com.sephirita.mangarift.presentation.component.load

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sephirita.mangarift.presentation.model.StateAnimationType

@Composable
fun Loader(loadingAnimationType: StateAnimationType) {
    val loadingComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url(loadingAnimationType.animationUrl)
    )
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = loadingComposition,
            iterations = LottieConstants.IterateForever
        )
    }
}