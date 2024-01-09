package com.mommydndn.app.deprecated

import java.text.NumberFormat
import java.util.Locale

object NumberUtils {
    fun getPriceString(value : Int): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        val formattedPrice = numberFormat.format(value)
        return formattedPrice
    }


    fun getPrice(value: String): Int? {
        val cleanValue = value.replace(",", "")
        return try {
            cleanValue.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}