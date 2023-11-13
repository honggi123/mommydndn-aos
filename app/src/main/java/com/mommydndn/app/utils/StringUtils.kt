package com.mommydndn.app.utils

object StringUtils {
    fun getConcatenatedCommasString(data: List<String>): String {
        return when {
            data.size < 3 || data.size == 3 -> data.joinToString(",")
            data.size > 3 -> "${data.take(3).joinToString(",")} 외 ${data.size - 3}"
            else -> {
                ""
            }
        }
    }

    fun getConcatenatedString(data: List<String>): String {
        return when {
            data.size < 3 || data.size == 3 -> data.joinToString("")
            data.size > 3 -> "${data.take(3).joinToString("")} 외 ${data.size - 3}"
            else -> {
                ""
            }
        }
    }

}