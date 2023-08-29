package com.mommydndn.app.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mommydndn.app.R

private val notosanskr = FontFamily(
    Font(R.font.noto_sans_kr_bold, FontWeight.Bold),
    Font(R.font.noto_sans_kr_medium, FontWeight.Medium),
    Font(R.font.noto_sans_kr_regular, FontWeight.Normal)
)

private val defaultTypography = Typography()

val MommydndnTypography = Typography(
    defaultFontFamily = notosanskr,
)

val Typography.caption100: TextStyle
    @Composable get() = caption.copy(
        fontSize = 11.sp,
    )

val Typography.caption200: TextStyle
    @Composable get() = caption.copy(
        fontSize = 13.sp
    )

val Typography.paragraph300: TextStyle
    @Composable get() = TextStyle(
        fontSize = 15.sp,
        fontFamily = notosanskr
    )

val Typography.paragraph400: TextStyle
    @Composable get() = TextStyle(
        fontSize = 17.sp,
        fontFamily = notosanskr
    )

val Typography.paragraph500: TextStyle
    @Composable get() = TextStyle(
        fontSize = 19.sp,
        fontFamily = notosanskr
    )

val Typography.heading600: TextStyle
    @Composable get() = TextStyle(
        fontSize = 20.sp,
        fontFamily = notosanskr
    )

val Typography.heading700: TextStyle
    @Composable get() = TextStyle(
        fontSize = 22.sp,
        fontFamily = notosanskr
    )

val Typography.heading800: TextStyle
    @Composable get() = TextStyle(
        fontSize = 24.sp,
        fontFamily = notosanskr
    )

val Typography.heading900: TextStyle
    @Composable get() = TextStyle(
        fontSize = 26.sp,
        fontFamily = notosanskr
    )

val Typography.heading1000: TextStyle
    @Composable get() = TextStyle(
        fontSize = 28.sp,
        fontFamily = notosanskr
    )

