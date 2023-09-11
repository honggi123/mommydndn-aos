package com.mommydndn.app.ui

object SignInNav : Destination {
    override val route: String = NavigationRouteName.SIGN_IN
    override val title: String = NavigationTitle.SIGN_IN
}

object TypeChoiceNav : Destination {
    override val route: String = NavigationRouteName.TYPE_CHOICE
    override val title: String = NavigationTitle.TYPE_CHOICE
}

object TownCheckNav : Destination {
    override val route: String = NavigationRouteName.TOWN_CHECK
    override val title: String = NavigationTitle.TOWN_CHECK
}

interface Destination {
    val route: String
    val title: String
}
object NavigationRouteName {
    const val SIGN_IN = "로그인"
    const val TYPE_CHOICE = "개인&기업선택"
    const val TOWN_CHECK = "위치확인"
}

object NavigationTitle {
    const val SIGN_IN = "로그인"
    const val TYPE_CHOICE = "개인&기업선택"
    const val TOWN_CHECK = "위치확인"
}