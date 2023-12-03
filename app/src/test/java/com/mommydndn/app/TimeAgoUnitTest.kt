package com.mommydndn.app

import org.junit.Test
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime

class TimeAgoUnitTest {

    @Test
    fun timeAgo() {
        val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))

        val createdAt = now.minusHours(2).minusMinutes(23)

        val duration = Duration.between(createdAt, now)

        val days = duration.toDays()
        val hours = duration.toHours()
        val minutes = duration.toMinutes()


    }
}