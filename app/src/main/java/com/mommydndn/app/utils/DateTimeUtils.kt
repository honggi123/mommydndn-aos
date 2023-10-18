package com.mommydndn.app.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {

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

    fun getLocalDate(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): LocalDate {
        return LocalDate.of(year, month, dayOfMonth)
    }

    fun getLocalDateText(
        localDate: LocalDate
    ): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return localDate.format(formatter)
    }

    fun getLocalTime(hour: Int, minute: Int): LocalTime {
        return LocalTime.of(hour, minute)
    }

    fun getLocalTimeText(
        localTime: LocalTime
    ): String {
        val formatter = DateTimeFormatter.ofPattern("h:m")
        return localTime.format(formatter)
    }


}