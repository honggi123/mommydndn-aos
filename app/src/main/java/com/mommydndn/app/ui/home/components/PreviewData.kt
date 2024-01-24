package com.mommydndn.app.ui.home.components

import com.mommydndn.app.ui.components.banner.BannerUiModel
import com.mommydndn.app.ui.components.notification.NotificationSetting

val banners: List<BannerUiModel> = listOf(
    BannerUiModel(
        id = 0,
        imageUrl = "https://images.pexels.com/photos/11634900/pexels-photo-11634900.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        url = ""
    ),
    BannerUiModel(
        id = 1,
        imageUrl = "https://images.pexels.com/photos/7050090/pexels-photo-7050090.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        url = ""
    )
)

val nearestCareProviders: List<NearestCareWorkerUiModel> = listOf(
    NearestCareWorkerUiModel(
        "",
        "세아쌤",
        "30대",
        "여성",
        "육아",
    ),
    NearestCareWorkerUiModel(
        "",
        "수아",
        "20대",
        "여성",
        "요양",
    ),
    NearestCareWorkerUiModel(
        "",
        "유린",
        "20대",
        "여성",
        "등하원",
    ),
    NearestCareWorkerUiModel(
        "",
        "김민지",
        "30대",
        "여성",
        "가사",
    ),
    NearestCareWorkerUiModel(
        "",
        "윤아쌤",
        "20대",
        "여성",
        "육아",
    ),
)

val nearbyJobOpenings: List<NearbyCareJobUiModel> = listOf(
    NearbyCareJobUiModel(
        careType = "육아",
        title = "2일간 풀타임으로 봐주실 분을 구합니다",
        neighborhood = "반포동",
        pay = "시급 12,000원",
    ),
    NearbyCareJobUiModel(
        careType = "요양",
        title = "목금 5시간동안 봐주실 분 찾습니다",
        neighborhood = "반포동",
        pay = "시급 12,000원",
    ),
    NearbyCareJobUiModel(
        careType = "가사",
        title = "담주 화목 봐주실 분 계실까요",
        neighborhood = "반포동",
        pay = "시급 12,000원",
    ),
    NearbyCareJobUiModel(
        careType = "등하원",
        title = "내일 저녁 3시간동안 봐주실 분",
        neighborhood = "반포동",
        pay = "시급 11,000원",
    ),
    NearbyCareJobUiModel(
        careType = "등하원",
        title = "오늘 당장 구해요",
        neighborhood = "서초동",
        pay = "시급 10,000원",
    )
)

val notificationSettings: List<NotificationSetting> = listOf(
    NotificationSetting(
        id = 0,
        name = "면접 요청 알림",
    ),
    NotificationSetting(
        id = 1,
        name = "댓글 알림",
    ),
    NotificationSetting(
        id = 2,
        name = "계약 상태 알림",
    ),
    NotificationSetting(
        id = 3,
        name = "결제 관련 알림",
    ),
    NotificationSetting(
        id = 4,
        name = "앱 정보 알림",
    ),
)