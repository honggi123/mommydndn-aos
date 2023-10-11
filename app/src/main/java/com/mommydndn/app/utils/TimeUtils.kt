package com.mommydndn.app.utils

object TimeUtils {

    fun formatTimeAgo(utcTimeStamp: Long): String {
        val currentTimeMillis = System.currentTimeMillis()
        val timeDifferenceMillis = currentTimeMillis - utcTimeStamp

        val minutesDifference = timeDifferenceMillis / (1000 * 60)
        val hoursDifference = minutesDifference / 60
        val daysDifference = hoursDifference / 24

        return when {
            minutesDifference < 60 -> "$minutesDifference 분전"
            hoursDifference < 24 -> "$hoursDifference 시간전"
            daysDifference < 30 -> "$daysDifference 일전"
            else -> ""
        }
    }
}