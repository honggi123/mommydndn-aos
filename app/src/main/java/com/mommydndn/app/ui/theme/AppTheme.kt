package com.mommydndn.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val darkColors = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val lightColors = lightColors(
    primary = White,
    primaryVariant = Purple700,
    secondary = Teal200
)

@Composable
internal fun MommydndnTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}
