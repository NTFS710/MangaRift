package com.sephirita.mangarift.presentation.screen.error

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.presentation.component.header.DetailHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ErrorScreen(
    enabled: Boolean,
    onBackPressed: () -> Unit,
    tryAgain: () -> Unit
) {
    Scaffold(
        topBar = { DetailHeader(onBackPressed = onBackPressed) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                onClick = tryAgain,
                enabled = enabled
            ) {
                Text(text = "Tentar novamente")
            }
        }
    }
}