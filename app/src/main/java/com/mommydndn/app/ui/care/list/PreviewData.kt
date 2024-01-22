package com.mommydndn.app.ui.care.list

import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.PayPeriod
import com.mommydndn.app.domain.model.WorkPeriod
import com.mommydndn.app.ui.care.list.agency.CareAgencyUiModel
import com.mommydndn.app.ui.care.list.job.CareJobUiModel
import com.mommydndn.app.ui.care.list.worker.CareWorkerUiModel
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime

internal val jobs = listOf(
    CareJobUiModel(
        workPeriod = WorkPeriod.Regular,
        careTypes = setOf(CareType.SeniorCare),
        isClosed = false,
        title = "아버지 보살펴 주실 분을 구합니다",
        isLiked = true,
        neighborhoodName = "이문1동",
        createdAt = ZonedDateTime.now().minusMinutes(14),
        daysOfWeek = listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.FRIDAY,
        ),
        startTime = LocalTime.of(17, 0),
        endTime = LocalTime.of(22, 0),
        payPeriod = PayPeriod.Hourly,
        pay = 12000,
    ),
    CareJobUiModel(
        workPeriod = WorkPeriod.Regular,
        careTypes = setOf(
            CareType.ChildCare,
            CareType.Housekeeping,
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
        payPeriod = PayPeriod.Hourly,
        pay = 12000,
    ),
    CareJobUiModel(
        workPeriod = WorkPeriod.OneTime,
        careTypes = setOf(CareType.ChildCare),
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
        payPeriod = PayPeriod.Hourly,
        pay = 10000,
    ),
    CareJobUiModel(
        workPeriod = WorkPeriod.OneTime,
        careTypes = setOf(CareType.ChildCare),
        isClosed = false,
        title = "오늘 당장 구해요",
        isLiked = false,
        neighborhoodName = "서초동",
        createdAt = ZonedDateTime.now()
            .minusDays(2),
        daysOfWeek = listOf(DayOfWeek.MONDAY),
        startTime = LocalTime.of(10, 0),
        endTime = LocalTime.of(20, 30),
        payPeriod = PayPeriod.Hourly,
        pay = 15000,
    )
)

internal val workers = listOf(
    CareWorkerUiModel(
        profileImageUrl = "http://www.bing.com/search?q=menandri",
        dndnCertified = true,
        nickname = "세아쌤",
        neighborhood = "반포동 외 24개 동네",
        dndnScore = 5.0,
        isLiked = true,
        ageRangeAndGender = "30대 여성",
        careTypes = setOf(
            CareType.ChildCare,
            CareType.Housekeeping,
        ),
        matchingCount = 24,
        reviewCount = 14,
        responseRate = 100,
    ),
    CareWorkerUiModel(
        profileImageUrl = "http://www.bing.com/search?q=menandri",
        dndnCertified = false,
        nickname = "수아",
        neighborhood = "반포동 외 9개 동네",
        dndnScore = 5.0,
        isLiked = true,
        ageRangeAndGender = "20대 여성",
        careTypes = setOf(
            CareType.Housekeeping,
            CareType.SeniorCare,
        ),
        matchingCount = 24,
        reviewCount = 14,
        responseRate = 100,
    )
)

internal val agencies = listOf(
    CareAgencyUiModel(
        dndnCertified = true,
        name = "피카부 베이비시터",
        neighborhood = "서초동 외 9개 동네",
        dndnScore = 5.0,
        careTypes = CareType.entries.toSet(),
        profileImageUrl = "http://www.bing.com/search?q=leo",
        isLiked = true,
        matchingCount = 821,
        reviewCount = 624,
        responseRate = 100,
    ),
    CareAgencyUiModel(
        dndnCertified = true,
        name = "피카부 베이비시터",
        neighborhood = "서초동 외 9개 동네",
        dndnScore = 5.0,
        careTypes = setOf(
            CareType.ChildCare,
            CareType.Housekeeping,
            CareType.SeniorCare,
        ),
        profileImageUrl = "http://www.bing.com/search?q=leo",
        isLiked = false,
        matchingCount = 624,
        reviewCount = 234,
        responseRate = 100,
    )
)