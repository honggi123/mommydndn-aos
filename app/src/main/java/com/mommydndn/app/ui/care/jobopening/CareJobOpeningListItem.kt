package com.mommydndn.app.ui.care.jobopening

import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.PayPeriod
import com.mommydndn.app.domain.model.care.WorkPeriod
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime

data class CareJobOpeningListItem(
    val workPeriod: WorkPeriod,
    val careTypes: List<CareType>,
    val isClosed: Boolean,
    val title: String,
    val isLiked: Boolean,
    val neighborhoodName: String,
    val createdAt: ZonedDateTime,
    val daysOfWeek: List<DayOfWeek>,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val payPeriod: PayPeriod,
    val pay: Int,
)

val mockCareJobOpeningListItems = buildList(capacity = 4) {
    add(
        CareJobOpeningListItem(
            workPeriod = WorkPeriod.REGULAR,
            careTypes = listOf(
                CareType.SENIOR_CARE,
            ),
            isClosed = false,
            title = "아버지 보살펴 주실 분을 구합니다",
            isLiked = true,
            neighborhoodName = "이문1동",
            createdAt = ZonedDateTime.now()
                .minusMinutes(14),
            daysOfWeek = listOf(
                DayOfWeek.MONDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.FRIDAY,
            ),
            startTime = LocalTime.of(17, 0),
            endTime = LocalTime.of(22, 0),
            payPeriod = PayPeriod.HOURLY,
            pay = 12000,
        )
    )

    add(
        CareJobOpeningListItem(
            workPeriod = WorkPeriod.REGULAR,
            careTypes = listOf(
                CareType.CHILD_CARE,
                CareType.HOUSEKEEPING,
            ),
            isClosed = false,
            title = "2일간 풀타임으로 아이 둘 맡아주실 분 구해요",
            isLiked = false,
            neighborhoodName = "반포동",
            createdAt = ZonedDateTime.now()
                .minusHours(1),
            daysOfWeek = listOf(
                DayOfWeek.MONDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.FRIDAY,
            ),
            startTime = LocalTime.of(17, 0),
            endTime = LocalTime.of(22, 0),
            payPeriod = PayPeriod.HOURLY,
            pay = 12000,
        )
    )

    add(
        CareJobOpeningListItem(
            workPeriod = WorkPeriod.ONE_TIME,
            careTypes = listOf(
                CareType.CHILD_CARE,
            ),
            isClosed = true,
            title = "담주 화목 봐주실 분 구해요!",
            isLiked = true,
            neighborhoodName = "반포동",
            createdAt = ZonedDateTime.now()
                .minusHours(2)
                .minusMinutes(19),
            daysOfWeek = listOf(
                DayOfWeek.TUESDAY,
                DayOfWeek.THURSDAY,
            ),
            startTime = LocalTime.of(10, 0),
            endTime = LocalTime.of(19, 0),
            payPeriod = PayPeriod.HOURLY,
            pay = 10000,
        )
    )

    add(
        CareJobOpeningListItem(
            workPeriod = WorkPeriod.ONE_TIME,
            careTypes = listOf(CareType.CHILD_CARE),
            isClosed = false,
            title = "오늘 당장 구해요",
            isLiked = false,
            neighborhoodName = "서초동",
            createdAt = ZonedDateTime.now()
                .minusDays(2),
            daysOfWeek = listOf(DayOfWeek.MONDAY),
            startTime = LocalTime.of(10, 0),
            endTime = LocalTime.of(20, 30),
            payPeriod = PayPeriod.HOURLY,
            pay = 15000,
        )
    )
}