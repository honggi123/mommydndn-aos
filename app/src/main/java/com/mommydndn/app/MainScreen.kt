package com.mommydndn.app

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.mommydndn.app.ui.component.common.Header
import com.mommydndn.app.ui.main.MainHomeScreen
import com.mommydndn.app.ui.signin.SignInScreen
import com.mommydndn.app.ui.signup.TownCheckScreen
import com.mommydndn.app.ui.signup.UserTypeChoiceScreen
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.viewmodel.SignUpViewModel

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
        topBar = {
            if (MainNav.isMainRoute(currentRoute)) {
                MainTopBar(navController)
            }
        },
        bottomBar = {
            if (MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        }
    ) {
        MainNavigationScreen(navController, googleSignInClient, fusedLocationClient)
    }
}


@Composable
fun MainTopBar(
    navHostController: NavHostController
) {
    Header(leftContent = {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier = Modifier
                .size(36.dp)
        )
    }, rightContent = {
        Image(
            painter = painterResource(id = R.drawable.ic_headset),
            contentDescription = "",
            modifier = Modifier
                .size(36.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "",
            modifier = Modifier
                .size(36.dp)
        )
    }
    )
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
            BottomNavigationItem(selected = currentRoute == item.route, onClick = {}, icon = {
                Icon(
                    painterResource(id = item.iconRes),
                    "",
                    tint = if (currentRoute == item.route) Grey800 else Grey300
                )
            })
        }
    }
}

@Composable
fun MainNavigationScreen(
    navController: NavHostController,
    googleSignInClient: GoogleSignInClient,
    fusedLocationClient: FusedLocationProviderClient
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

    NavHost(navController = navController, startDestination = MainNav.Home.route) {
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

