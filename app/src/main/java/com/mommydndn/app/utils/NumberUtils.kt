package com.mommydndn.app.utils

import com.mommydndn.app.data.model.JobOffer
import java.text.NumberFormat
import java.util.Locale

object NumberUtils {
    fun formatPriceString(value : Int): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        val formattedPrice = numberFormat.format(value)
        return formattedPrice
    }
}