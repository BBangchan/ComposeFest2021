package com.codelab.theming.ui.start.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color


// 색깔을 정의 할 때, 색상의 이름을 primary와 같은 의미론적인것보다, 색상 값을 기준으로한 이름을 짓는다.
val Red200 = Color(0xfff297a2)
val Red300 = Color(0xffea6d7e)
val Red700 = Color(0xffdd0d3c)
val Red800 = Color(0xffd00036)
val Red900 = Color(0xffd20029)

private val LightColors = lightColors(
    // 브랜드에 별도에 기본 색상(primary)과 보조 색상(secondary)이 없는 경우 둘다 동일한 색상을 하는 것이 좋다.
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800
)

private val DarkColors = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.Black,
    error = Red200
)