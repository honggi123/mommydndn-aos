package com.mommydndn.app.utils

import android.util.Log
import com.mommydndn.app.data.model.common.DayOfWeekType
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateTimeUtils {

    fun getFormattedTimeAgo(timeStamp: Long): String {
        val currentTimeMillis = System.currentTimeMillis()
        val timeDifferenceMillis = currentTimeMillis - timeStamp

        val minutesDifference = timeDifferenceMillis / (1000 * 60)
        val hoursDifference = minutesDifference / 60
        val daysDifference = hoursDifference / 24
        Log.e("minutesDifference", minutesDifference.toString())
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
        val formatter = DateTimeFormatter.ofPattern("hh:mm")
        return localTime.format(formatter)
    }

    fun formatToMonthDay(
        localDate: LocalDate
    ): String {
        val formatter = DateTimeFormatter.ofPattern("M월 d일", Locale.getDefault())
        return localDate.format(formatter)
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

    fun formatTimestampToYearMonthDay(timestamp: Long): String {
        val date = Date(timestamp)
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
        return dateFormat.format(date)
    }

    fun formatLocalDateRange(startDate: LocalDate?, endDate: LocalDate?): String {
        if (startDate == null && endDate == null) return ""

        val startCalendar = convertLocalDateToCalendar(startDate!!)

        val endCalendar = convertLocalDateToCalendar(endDate!!)

        val startDateFormatter = SimpleDateFormat("M월 d일", java.util.Locale.getDefault())

        val startFormatted = startDateFormatter.format(startCalendar.time)

        val endDateFormatter = SimpleDateFormat("d일", java.util.Locale.getDefault())

        val endFormatted = endDateFormatter.format(endCalendar.time)

        val daysOfWeek = mutableListOf<String>()
        val daysOfWeekShort = SimpleDateFormat("E", java.util.Locale.getDefault())

        val dayOfWeekKorean = arrayOf("일", "월", "화", "수", "목", "금", "토")

        var current = startCalendar.clone() as Calendar
        while (current <= endCalendar) {
            val dayOfWeekShort = daysOfWeekShort.format(current.time)
            val dayOfWeekKoreanIndex = when (dayOfWeekShort) {
                "Sun" -> 0
                "Mon" -> 1
                "Tue" -> 2
                "Wed" -> 3
                "Thu" -> 4
                "Fri" -> 5
                "Sat" -> 6
                else -> -1
            }

            val dayOfWeekKoreanString = if (dayOfWeekKoreanIndex >= 0) {
                dayOfWeekKorean[dayOfWeekKoreanIndex]
            } else {
                dayOfWeekShort
            }

            daysOfWeek.add(dayOfWeekKoreanString)
            current.add(Calendar.DAY_OF_MONTH, 1)
        }

        val daysOfWeekText = daysOfWeek.distinct().joinToString(",")

        return "$startFormatted ~ $endFormatted ($daysOfWeekText)"
    }

    private fun convertLocalDateToCalendar(localDate: LocalDate): Calendar {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.set(Calendar.YEAR, localDate.year)
        calendar.set(Calendar.MONTH, localDate.monthValue - 1)
        calendar.set(Calendar.DAY_OF_MONTH, localDate.dayOfMonth)
        return calendar
    }

    fun formatLocalTime(localTime: LocalTime?): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return localTime?.format(formatter) ?: ""
    }
}