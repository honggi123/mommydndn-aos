package com.mommydndn.app.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

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
        val formatter = DateTimeFormatter.ofPattern("hh:mm:ss")
        return localTime.format(formatter)
    }

    fun getTimestampByLocalDate(localDate: LocalDate?): Long? {
        val localDateTime = localDate?.atStartOfDay()
        val instant = localDateTime?.toInstant(ZoneOffset.UTC)
        return instant?.toEpochMilli()
    }

    fun getTimestampByLocalTime(localTime: LocalTime?): Long? {
        val localDate = LocalDate.now()
        val localDateTime = LocalDateTime.of(localDate, localTime)
        val instant = localDateTime.toInstant(ZoneOffset.UTC)
        return instant.toEpochMilli()
    }

    fun formatTimestampToMonthDay(timestamp: Long?): String {
        try {
            val date = Date(timestamp!! * 1000)
            val sdf = SimpleDateFormat("MM월 dd일", Locale.getDefault())
            return sdf.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }


}