package com.mommydndn.app.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mommydndn.app.R

val NotoSansKR = FontFamily(
    Font(R.font.noto_sans_kr_regular, FontWeight.Normal),
    Font(R.font.noto_sans_kr_medium, FontWeight.Medium),
    Font(R.font.noto_sans_kr_bold, FontWeight.Bold),
)

val Typography = Typography(defaultFontFamily = NotoSansKR)

val Typography.caption100: TextStyle
    @Composable
    get() = caption.merge(
        fontSize = 11.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

val Typography.caption200: TextStyle
    @Composable
    get() = caption.merge(
        fontSize = 13.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

val Typography.paragraph300: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 15.sp,
        fontFamily = NotoSansKR,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

val Typography.paragraph400: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 17.sp,
        fontFamily = NotoSansKR,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

val Typography.paragraph500: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 19.sp,
        fontFamily = NotoSansKR,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

val Typography.heading600: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 20.sp,
        fontFamily = NotoSansKR,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

val Typography.heading700: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 22.sp,
        fontFamily = NotoSansKR,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

val Typography.heading800: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 24.sp,
        fontFamily = NotoSansKR,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

val Typography.heading900: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 26.sp,
        fontFamily = NotoSansKR,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

val Typography.heading1000: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 28.sp,
        fontFamily = NotoSansKR,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )
