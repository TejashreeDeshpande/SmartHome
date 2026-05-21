package com.example.smarthome.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = Copper,
    secondary = GoldAccent,
    background = BackgroundDark,
    surface = CardColor,
    onPrimary = WarmCream,
    onSecondary = WarmCream,
    onBackground = WarmCream,
    onSurface = WarmCream
)

@Composable
fun SmartHomeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = Typography,
        content = content
    )
}