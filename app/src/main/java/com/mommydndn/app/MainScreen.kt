package com.mommydndn.app

import android.net.Uri
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.mommydndn.app.ui.MainNav
import com.mommydndn.app.ui.SignInNav
import com.mommydndn.app.ui.TownCheckNav
import com.mommydndn.app.ui.TypeChoiceNav
import com.mommydndn.app.ui.care.CareScreen
import com.mommydndn.app.ui.home.MainHomeScreen
import com.mommydndn.app.ui.signin.SignInScreen
import com.mommydndn.app.ui.signup.NearestChoiceScreen
import com.mommydndn.app.ui.signup.UserTypeChoiceScreen
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.home.HomeViewModel
import com.mommydndn.app.ui.signup.SignUpViewModel
import com.mommydndn.app.utils.NavigationUtils

@Composable
fun MainScreen(
    googleSignInClient: GoogleSignInClient,
    fusedLocationClient: FusedLocationProviderClient
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val signUpViewModel = hiltViewModel<SignUpViewModel>()

    NavHost(
        navController = navController,
        startDestination = SignInNav.route
    ) {
        mommydndnNavGraph(
            navController,
            googleSignInClient,
            fusedLocationClient,
            signUpViewModel
        )
    }

}

@Composable
fun MainBottomNavigationBar(
    navController: NavHostController,
    currentRoute: String,
) {
    val bottomNavigationItems = listOf(
        MainNav.Home,
        MainNav.Care
    )

    BottomNavigation {
        bottomNavigationItems.onEach { item ->
            BottomNavigationItem(selected = currentRoute == item.route, onClick = {
                NavigationUtils.navigate(
                    navController, item.route,
                    navController.graph.startDestinationRoute
                )
            }, icon = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = item.iconRes),
                        "",
                        tint = if (currentRoute == item.route) Grey800 else Grey300
                    )
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Medium,
                            color = if (currentRoute == item.route) Grey800 else Grey300,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        )
                    )
                }
            })
        }
    }
}



private fun NavGraphBuilder.mommydndnNavGraph(
    navController: NavHostController,
    googleSignInClient: GoogleSignInClient,
    fusedLocationClient: FusedLocationProviderClient,
    signUpViewModel: SignUpViewModel,
) {

    val slideEnterTransition = slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    val slideExitTransition = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    composable(
        route = SignInNav.route,
    ) {
        SignInScreen(navHostController = navController, googleSignInClient = googleSignInClient)
    }

    composable(
        route = TypeChoiceNav.routeWithArgName(),
        arguments = TypeChoiceNav.arguments,

        enterTransition = { slideEnterTransition },
        exitTransition = { slideExitTransition }
    ) { it ->
        val signUpInfo = TypeChoiceNav.findArgument(it)
        val accessToken = Uri.decode(signUpInfo?.accessToken)

        UserTypeChoiceScreen(
            signUpInfo = signUpInfo?.copy(accessToken = accessToken),
            navHostController = navController,
            viewModel = signUpViewModel
        )
    }

    composable(
        route = TownCheckNav.route,
        enterTransition = { slideEnterTransition },
        exitTransition = { slideExitTransition }
    ) {
        NearestChoiceScreen(
            navHostController = navController,
            fusedLocationClient = fusedLocationClient,
            viewModel = signUpViewModel
        )
    }

    composable(
        route = MainNav.Home.route,
        enterTransition = { slideEnterTransition },
        exitTransition = { slideExitTransition }
    ) {
        MainHomeScreen(navHostController = navController)
    }

    composable(
        route = MainNav.Care.route,
        enterTransition = { slideEnterTransition },
        exitTransition = { slideExitTransition }
    ) {
        CareScreen(navHostController = navController)
    }


}

