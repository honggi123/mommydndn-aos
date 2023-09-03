package com.mommydndn.app.ui

object SignInNav : Destination {
    override val route: String = NavigationRouteName.SIGN_IN
    override val title: String = NavigationTitle.SIGN_IN
}

object MemberTypeChoiceNav : Destination {
    override val route: String = NavigationRouteName.MEMBER_TYPE_CHOICE
    override val title: String = NavigationTitle.MEMBER_TYPE_CHOICE
}

interface Destination {
    val route: String
    val title: String
}
object NavigationRouteName {
    const val SIGN_IN = "로그인"
    const val MEMBER_TYPE_CHOICE = "개인&기업선택"
}

object NavigationTitle {
    const val SIGN_IN = "로그인"
    const val MEMBER_TYPE_CHOICE = "개인&기업선택"
}