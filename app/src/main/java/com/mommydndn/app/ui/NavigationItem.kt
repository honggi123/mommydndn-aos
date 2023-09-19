package com.mommydndn.app.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.kakao.sdk.common.KakaoSdk.type
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.utils.GsonUtils

object SignInNav : Destination {
    override val route: String = NavigationRouteName.SIGN_IN
    override val title: String = NavigationTitle.SIGN_IN
}

object TypeChoiceNav : Destination {
    override val route: String = NavigationRouteName.TYPE_CHOICE
    override val title: String = NavigationTitle.TYPE_CHOICE
    val argName: String = "signUpInfo"

    val arguments: List<NamedNavArgument> = listOf(
        navArgument(argName) { type= NavType.StringType}
    )

    fun routeWithArgName() = "$route/{$argName}"

    fun navigateWithArg(item: SignUpInfo): String  {
        val arg = GsonUtils.toJson(item)
        return "${TypeChoiceNav.route}/$arg"
    }

    fun findArgument(navBackStackEntry: NavBackStackEntry): SignUpInfo? {
        val signUpInfoString = navBackStackEntry.arguments?.getString(argName)
        return GsonUtils.fromJson<SignUpInfo>(signUpInfoString)
    }
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