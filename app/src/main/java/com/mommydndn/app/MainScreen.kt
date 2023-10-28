package com.mommydndn.app

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.mommydndn.app.ui.navigation.MainNav
import com.mommydndn.app.ui.navigation.SignInNav
import com.mommydndn.app.ui.navigation.TownCheckNav
import com.mommydndn.app.ui.navigation.TypeChoiceNav
import com.mommydndn.app.ui.features.care.CareScreen
import com.mommydndn.app.ui.features.care.joboffer.write.JobOfferWriteScreen
import com.mommydndn.app.ui.features.care.joboffer.write.JobOfferWriteViewModel
import com.mommydndn.app.ui.features.care.joboffer.write.LocationSearchScreen
import com.mommydndn.app.ui.features.care.joboffer.write.preview.JobOfferPreviewScreen
import com.mommydndn.app.ui.features.home.MainHomeScreen
import com.mommydndn.app.ui.features.signin.SignInScreen
import com.mommydndn.app.ui.features.signup.NearestChoiceScreen
import com.mommydndn.app.ui.features.signup.UserTypeChoiceScreen
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.features.signup.SignUpViewModel
import com.mommydndn.app.ui.navigation.JobOfferWriteNav
import com.mommydndn.app.ui.navigation.JobOfferWritePreviewNav
import com.mommydndn.app.ui.navigation.LocationSearchNav
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.utils.NavigationUtils

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    googleSignInClient: GoogleSignInClient,
    fusedLocationClient: FusedLocationProviderClient
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        },
        floatingActionButton = {
            if (MainNav.isFloatingActionBarVisible(currentRoute)) {
                FloatingActionButton(
                    onClick = { NavigationUtils.navigate(navController, JobOfferWriteNav.route) },
                    modifier = Modifier
                        .size(72.dp),
                    backgroundColor = Salmon600
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pencil),
                        contentDescription = null,
                        tint = White
                    )
                }
            }
        }
    ) {
        MainNavigationScreen(navController, googleSignInClient, fusedLocationClient)
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


@Composable
fun MainNavigationScreen(
    navController: NavHostController,
    googleSignInClient: GoogleSignInClient,
    fusedLocationClient: FusedLocationProviderClient,
) {
    val signUpViewModel = hiltViewModel<SignUpViewModel>()
    val jobOfferWriteViewModel = hiltViewModel<JobOfferWriteViewModel>()

    val slideEnterTransition = slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    val slideExitTransition = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    NavHost(navController = navController, startDestination = SignInNav.route) {
        composable(
            route = SignInNav.route,
        ) {
            SignInScreen(
                navHostController = navController,
                googleSignInClient = googleSignInClient
            )
        }

        composable(
            route = TypeChoiceNav.routeWithArgName(),
            arguments = TypeChoiceNav.arguments,

            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
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
            MainHomeScreen(navController = navController)
        }

        composable(
            route = MainNav.Care.route
        ) {
            CareScreen(navController = navController)
        }

        composable(
            route = JobOfferWriteNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            JobOfferWriteScreen(navController = navController, viewModel = jobOfferWriteViewModel)
        }

        composable(
            route = LocationSearchNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            LocationSearchScreen(navController = navController, viewModel = jobOfferWriteViewModel)
        }

        composable(
            route = JobOfferWritePreviewNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            JobOfferPreviewScreen(navController = navController)
        }
    }

}

