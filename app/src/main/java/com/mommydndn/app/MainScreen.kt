package com.mommydndn.app

import android.net.Uri
import android.util.Log
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
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mommydndn.app.ui.features.home.MainHomeScreen
import com.mommydndn.app.ui.features.signin.SignInScreen
import com.mommydndn.app.ui.features.signup.location.LocationRoute
import com.mommydndn.app.ui.features.signup.SignUpViewModel
import com.mommydndn.app.ui.features.signup.user_type.UserTypeRoute
import com.mommydndn.app.ui.navigation.LocationSearchNav
import com.mommydndn.app.ui.navigation.MainNav
import com.mommydndn.app.ui.navigation.SignInNav
import com.mommydndn.app.ui.navigation.UserTypeNav
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.util.NavigationUtils

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if (MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        },
        floatingActionButton = {
            if (MainNav.isFloatingActionBarVisible(currentRoute)) {

            }
        }
    ) { paddingValues ->
        Log.d("mommy_dn_dn_android", "$paddingValues")

        MainNavigationScreen(
            navController,
            scaffoldState
        )
    }

}

@Composable
fun MainBottomNavigationBar(
    navController: NavHostController,
    currentRoute: String?,
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
                            color = if (currentRoute == item.route) Grey800 else Grey300
                        )
                    )
                }
            })
        }
    }
}


@Composable
fun MainNavigationScreen(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    val signUpViewModel = hiltViewModel<SignUpViewModel>()

    val slideEnterTransition = slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    val slideExitTransition = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    NavHost(navController = navController, startDestination = UserTypeNav.routeWithArgName()) {
        composable(
            route = SignInNav.route,
        ) {
            SignInScreen(navHostController = navController)
        }

        composable(
            route = UserTypeNav.routeWithArgName(),
            arguments = UserTypeNav.arguments,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            val signUpInfo = UserTypeNav.findArgument(it)
            val accessToken = Uri.decode(signUpInfo?.accessToken)

            UserTypeRoute(
                onUserTypeSelect = { NavigationUtils.navigate(navController, LocationSearchNav.route) },
                onBackButtonClick = { navController.popBackStack() },
                signUpInfo = signUpInfo?.copy(accessToken = accessToken),
                viewModel = signUpViewModel
            )
        }

        composable(
            route = LocationSearchNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            LocationRoute(
                onSignUpSuccess = { NavigationUtils.navigate(navController, MainNav.Home.route) },
                onBackButtonClick = { navController.popBackStack() },
                viewModel = signUpViewModel
            )
        }

        composable(
            route = MainNav.Home.route
        ) {
            MainHomeScreen(navController = navController)
        }


    }

}

