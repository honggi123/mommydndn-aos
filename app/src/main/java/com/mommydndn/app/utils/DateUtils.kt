package com.mommydndn.app.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    fun getFormattedTimeAgo(timeStamp: Long): String {
        val currentTimeMillis = System.currentTimeMillis()
        val timeDifferenceMillis = currentTimeMillis - timeStamp

        val minutesDifference = timeDifferenceMillis / (1000 * 60)
        val hoursDifference = minutesDifference / 60
        val daysDifference = hoursDifference / 24

        return when {
            minutesDifference < 60 -> "$minutesDifference" + "분전"
            hoursDifference < 24 -> "$hoursDifference" + "시간전"
            daysDifference < 30 -> "$daysDifference" + "일전"
            else -> ""
        }
    }

    fun dateToTimestamp(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): Long {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar.timeInMillis
    }

    fun timestampToDateString(
        timestamp: Long
    ): String {
        val dateFormat = SimpleDateFormat("yyyy:MM:dd", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }
}