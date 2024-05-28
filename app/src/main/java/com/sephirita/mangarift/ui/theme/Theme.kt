package com.sephirita.mangarift.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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