package com.wachon.spotiwrap.core.design.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wachon.spotiwrap.core.design.R

val LargeTitle: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.W900,
        fontSize = 27.sp
    )

val Title: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp
    )

val SmallTitle: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp
    )

val Body: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp
    )

val SubBody: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.W300,
        fontSize = 15.sp
    )

val FontFamily.Companion.Poppins: FontFamily
    get() = FontFamily(
        Font(R.font.poppins_light, FontWeight.W300),
        Font(R.font.poppins_regular, FontWeight.W400),
        Font(R.font.poppins_bold, FontWeight.W700),
        Font(R.font.poppins_black, FontWeight.W900),
    )

