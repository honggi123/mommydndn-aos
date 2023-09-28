@file:OptIn(ExperimentalAnimationApi::class)

package com.mommydndn.app

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mommydndn.app.ui.MainNav
import com.mommydndn.app.ui.SignInNav
import com.mommydndn.app.ui.TownCheckNav
import com.mommydndn.app.ui.TypeChoiceNav
import com.mommydndn.app.ui.main.MainHomeScreen
import com.mommydndn.app.ui.signin.SignInScreen
import com.mommydndn.app.ui.signup.TownCheckScreen
import com.mommydndn.app.ui.signup.UserTypeChoiceScreen
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            MommydndnaosTheme {
                Scaffold(
                    bottomBar = {
                        if (MainNav.isMainRoute(currentRoute)) {
                            MainBottomNavigationBar(navController, currentRoute)
                        }
                    }
                ) {
                    MainNavigationScreen(googleSignInClient, fusedLocationClient)
                }

            }
        }
    }
}

@Composable
fun MainBottomNavigationBar(
    navHostController: NavHostController,
    currentRoute: String?
) {
    val bottomNavigationItems = listOf(
        MainNav.Home
    )

    BottomNavigation {
        bottomNavigationItems.onEach { item ->
            BottomNavigationItem(selected = currentRoute == item.route, onClick = {}, icon = {})
        }
    }
}

@Composable
fun MainNavigationScreen(
    googleSignInClient: GoogleSignInClient,
    fusedLocationClient: FusedLocationProviderClient
) {
    val signUpViewModel = hiltViewModel<SignUpViewModel>()


    val navController = rememberNavController()
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
            TownCheckScreen(
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

    }
}

