package com.mommydndn.app.ui.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.CompanyPreview
import com.mommydndn.app.data.model.care.JobOfferPreview
import com.mommydndn.app.data.model.care.JobSeekerPreview
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.ui.navigation.NavigationRouteName.MAIN_CARE
import com.mommydndn.app.ui.navigation.NavigationRouteName.MAIN_HOME
import com.mommydndn.app.util.GsonUtils

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

object UserTypeNav : Destination {
    override val route: String = NavigationRouteName.SIGN_UP_USER_TYPE
    override val title: String = NavigationTitle.SIGN_UP_USER_TYPE
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

object LocationSearchNav : Destination {
    override val route: String = NavigationRouteName.SIGN_UP_LOCATION_SEARCH
    override val title: String = NavigationTitle.SIGN_UP_LOCATION_SEARCH
}

object JobOfferWriteNav : Destination {
    override val route: String = NavigationRouteName.JOB_OFFER_WRITE
    override val title: String = NavigationTitle.JOB_OFFER_WRITE
}

object JobOfferWritePreviewNav : Destination {
    override val route: String = NavigationRouteName.JOB_OFFER_WRITE_PREVIEW
    override val title: String = NavigationTitle.JOB_OFFER_WRITE_PREVIEW
    val argName: String = "JobOfferPreview"

    fun routeWithArgName() = "${JobOfferWritePreviewNav.route}/{${JobOfferWritePreviewNav.argName}}"

    val arguments: List<NamedNavArgument> = listOf(
        navArgument(argName) { type= NavType.StringType}
    )

    fun navigateWithArg(item: JobOfferPreview): String  {
        val arg = GsonUtils.toJson(item)
        return "${JobOfferWritePreviewNav.route}/$arg"
    }

    fun findArgument(navBackStackEntry: NavBackStackEntry): JobOfferPreview? {
        val jobOfferString = navBackStackEntry.arguments?.getString(argName)
        return GsonUtils.fromJson<JobOfferPreview>(jobOfferString)
    }
}

object JobOfferLocationSearchNav : Destination {
    override val route: String = NavigationRouteName.JOB_OFFER_LOCATION_SEARCH
    override val title: String = NavigationTitle.JOB_OFFER_LOCATION_SEARCH
}

object JobSeekerWriteNav : Destination {
    override val route: String = NavigationRouteName.JOB_SEEKER_WRITE
    override val title: String = NavigationTitle.JOB_SEEKER_WRITE
}

object JobSeekerWritePreviewNav : Destination {
    override val route: String = NavigationRouteName.JOB_SEEKER_WRITE_PREVIEW
    override val title: String = NavigationTitle.JOB_SEEKER_WRITE_PREVIEW
    val argName: String = "JobSeekerPreview"

    fun routeWithArgName() = "${JobSeekerWritePreviewNav.route}/{${JobSeekerWritePreviewNav.argName}}"

    val arguments: List<NamedNavArgument> = listOf(
        navArgument(argName) { type= NavType.StringType}
    )

    fun navigateWithArg(item: JobSeekerPreview): String  {
        val arg = GsonUtils.toJson(item)
        return "${JobSeekerWritePreviewNav.route}/$arg"
    }

    fun findArgument(navBackStackEntry: NavBackStackEntry): JobSeekerPreview? {
        val jobSeekerPreviewString = navBackStackEntry.arguments?.getString(argName)
        return GsonUtils.fromJson<JobSeekerPreview>(jobSeekerPreviewString)
    }
}

object JobSeekerLocationSearchNav : Destination {
    override val route: String = NavigationRouteName.JOB_SEEKER_LOCATION_SEARCH
    override val title: String = NavigationTitle.JOB_SEEKER_LOCATION_SEARCH
}

object CompanyWriteNav : Destination {
    override val route: String = NavigationRouteName.COMPANY_WRITE
    override val title: String = NavigationTitle.COMPANY_WRITE
}

object CompanyLocationSearchNav : Destination {
    override val route: String = NavigationRouteName.COMPANY_LOCATION_SEARCH
    override val title: String = NavigationTitle.COMPANY_LOCATION_SEARCH
}

object CompanyWritePreviewNav : Destination {
    override val route: String = NavigationRouteName.COMPANY_WRITE_PREVIEW
    override val title: String = NavigationTitle.COMPANY_WRITE_PREVIEW
    val argName: String = "CompanyWritePreview"

    fun routeWithArgName() = "${CompanyWritePreviewNav.route}/{${CompanyWritePreviewNav.argName}}"

    val arguments: List<NamedNavArgument> = listOf(
        navArgument(argName) { type= NavType.StringType}
    )

    fun navigateWithArg(item: CompanyPreview): String  {
        val arg = GsonUtils.toJson(item)
        return "${CompanyWritePreviewNav.route}/$arg"
    }

    fun findArgument(navBackStackEntry: NavBackStackEntry): CompanyPreview? {
        val companyPreviewString = navBackStackEntry.arguments?.getString(argName)
        return GsonUtils.fromJson<CompanyPreview>(companyPreviewString)
    }
}

interface Destination {
    val route: String
    val title: String
}

object NavigationRouteName {
    const val MAIN_HOME = "홈"
    const val MAIN_CARE = "돌봄"

    const val JOB_OFFER_WRITE = "구인글쓰기"
    const val JOB_OFFER_LOCATION_SEARCH = "구인글쓰기_위치검색"
    const val JOB_OFFER_WRITE_PREVIEW = "구인글쓰기_미리보기"

    const val JOB_SEEKER_WRITE = "구직글쓰기"
    const val JOB_SEEKER_LOCATION_SEARCH = "구직글쓰기_위치검색"
    const val JOB_SEEKER_WRITE_PREVIEW = "구직글쓰기_미리보기"

    const val COMPANY_WRITE = "업체글쓰기"
    const val COMPANY_WRITE_PREVIEW = "업체글쓰기_미리보기"
    const val COMPANY_LOCATION_SEARCH = "업체글쓰기_위치검색"

    const val SIGN_IN = "로그인"
    const val SIGN_UP_USER_TYPE = "개인&기업선택"
    const val SIGN_UP_LOCATION_SEARCH = "위치선택"
}

object NavigationTitle {
    const val MAIN_HOME = "홈"
    const val MAIN_CARE = "돌봄"

    const val JOB_OFFER_WRITE = "구인글쓰기"
    const val JOB_OFFER_LOCATION_SEARCH = "구인글쓰기_위치검색"
    const val JOB_OFFER_WRITE_PREVIEW = "구인글쓰기_미리보기"

    const val JOB_SEEKER_WRITE = "구직글쓰기"
    const val JOB_SEEKER_LOCATION_SEARCH = "구직글쓰기_위치검색"
    const val JOB_SEEKER_WRITE_PREVIEW = "구직글쓰기_미리보기"

    const val COMPANY_WRITE = "업체글쓰기"
    const val COMPANY_WRITE_PREVIEW = "업체글쓰기_미리보기"
    const val COMPANY_LOCATION_SEARCH = "업체글쓰기_위치검색"

    const val SIGN_IN = "로그인"
    const val SIGN_UP_USER_TYPE = "개인&기업선택"
    const val SIGN_UP_LOCATION_SEARCH = "위치선택"

}