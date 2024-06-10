package com.sephirita.mangarift.presentation.screen.splash


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.R
import com.sephirita.mangarift.presentation.screen.destinations.HomeScreenDestination
import com.sephirita.mangarift.presentation.screen.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Destination(start = true)
@Composable
fun Splash(navigator: DestinationsNavigator) {
    val viewModel: SplashViewModel = koinViewModel()
    val state by viewModel.splashState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    SplashContent()

    with(state) {
        AnimatedVisibility(
            visible = showBlur,
            enter = fadeIn(tween(300)),
            exit = fadeOut(tween(300)),
            modifier = Modifier.fillMaxSize().blur(10.dp)
        ) {
            SplashContent()
        }
        if (!showBlur) {
            coroutineScope.launch {
                delay(1000)
                navigator.navigate(HomeScreenDestination)
            }
        }
    }
}

@Composable
fun SplashContent() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF090909)),
        contentAlignment = Alignment.Center
    ) {
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.logo),
                contentScale = ContentScale.FillWidth,
                contentDescription = "Splash Screen",
            )
            Row(
                modifier = Modifier.align(Alignment.BottomCenter).offset(y = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Powered by MangaDex",
                    color = Color(0xFFFF6740),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.mangadex_logo),
                    contentDescription = "Logo MangaDex"
                )
            }
        }
    }
}