package com.mommydndn.app.ui.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mommydndn.app.R
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.ui.navigation.NavigationRouteName.MAIN_CARE
import com.mommydndn.app.ui.navigation.NavigationRouteName.MAIN_HOME
import com.mommydndn.app.utils.GsonUtils

sealed class MainNav(
    override val route: String,
    @DrawableRes val iconRes: Int,
    override val title: String
) : Destination {
    object Home : MainNav(MAIN_HOME, R.drawable.ic_home, NavigationTitle.MAIN_HOME)
    object Care : MainNav(MAIN_CARE, R.drawable.ic_baby, NavigationTitle.MAIN_CARE)

    companion object {
        fun isMainRoute(route: String?): Boolean {
            return when (route) {
                MAIN_HOME, MAIN_CARE -> true
                else -> false
            }
        }
        fun isFloatingActionBarVisible(route: String?): Boolean {
            return when (route) {
                MAIN_CARE -> true
                else -> false
            }
        }
    }
}

object SignInNav : Destination {
    override val route: String = NavigationRouteName.SIGN_IN
    override val title: String = NavigationTitle.SIGN_IN
}

object TypeChoiceNav : Destination {
    override val route: String = NavigationRouteName.TYPE_CHOICE
    override val title: String = NavigationTitle.TYPE_CHOICE
    val argName: String = "signUpInfo"

    val arguments: List<NamedNavArgument> = listOf(
        navArgument(argName) { type = NavType.StringType }
    )

    fun routeWithArgName() = "$route/{$argName}"

    fun navigateWithArg(item: SignUpInfo): String {
        val arg = GsonUtils.toJson(item)
        return "$route/$arg"
    }

    fun findArgument(navBackStackEntry: NavBackStackEntry): SignUpInfo? {
        val signUpInfoString = navBackStackEntry.arguments?.getString(argName)
        return GsonUtils.fromJson<SignUpInfo>(signUpInfoString)
    }
}

object TownCheckNav : Destination {
    override val route: String = NavigationRouteName.NEAREST_CHOICE
    override val title: String = NavigationTitle.NEAREST_CHOICE
}

object JobOfferWriteNav : Destination {
    override val route: String = NavigationRouteName.JOB_OFFER_WRITE
    override val title: String = NavigationTitle.JOB_OFFER_WRITE
}

interface Destination {
    val route: String
    val title: String
}

object NavigationRouteName {
    const val MAIN_HOME = "홈"
    const val MAIN_CARE = "돌봄"

    const val JOB_OFFER_WRITE = "구인글쓰기"

    const val SIGN_IN = "로그인"
    const val TYPE_CHOICE = "개인&기업선택"
    const val NEAREST_CHOICE = "근처위치선택"
}

object NavigationTitle {
    const val MAIN_HOME = "홈"
    const val MAIN_CARE = "돌봄"

    const val JOB_OFFER_WRITE = "구인글쓰기"

    const val SIGN_IN = "로그인"
    const val TYPE_CHOICE = "개인&기업선택"
    const val NEAREST_CHOICE = "근처위치선택"

}