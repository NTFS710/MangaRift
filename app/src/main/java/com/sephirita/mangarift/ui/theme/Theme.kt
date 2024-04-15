package com.sephirita.mangarift.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF714DD9),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF8058F3),
    onSecondary = Color(0xFFFFFFFF),
    tertiary = Color(0xFF5F42B3),
    onTertiary = Color(0xFFFFFFFF),
    surface = Color(0xFF232229),
    onSurface = Color(0xFFFFFFFF),
    background = Color(0xFF171719),
    onBackground = Color(0xFFFFFFFF)
)

@Composable
fun MangaRiftTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}