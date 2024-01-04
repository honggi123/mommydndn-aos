package com.mommydndn.app.feature.main

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
//            if (MainNav.isMainRoute(currentRoute)) {
//                MainBottomNavigationBar(navController, currentRoute)
//            }
        },
        floatingActionButton = {
//            if (MainNav.isFloatingActionBarVisible(currentRoute)) {
//
//            }
        }
    ) { paddingValues ->
        Log.d("mommy_dn_dn_android", "$paddingValues")

//        MainNavigationScreen(
//            navController,
//            scaffoldState
//        )
    }

}
//
//@Composable
//fun MainBottomNavigationBar(
//    navController: NavHostController,
//    currentRoute: String?,
//) {
//    val bottomNavigationItems = listOf(
//        MainNav.Home,
//        MainNav.Care
//    )
//
//    BottomNavigation {
//        bottomNavigationItems.onEach { item ->
//            BottomNavigationItem(selected = currentRoute == item.route, onClick = {
//                NavigationUtils.navigate(
//                    navController, item.route,
//                    navController.graph.startDestinationRoute
//                )
//            }, icon = {
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Icon(
//                        painterResource(id = item.iconRes),
//                        "",
//                        tint = if (currentRoute == item.route) Grey800 else Grey300
//                    )
//                    Text(
//                        text = item.title,
//                        style = MaterialTheme.typography.caption200.copy(
//                            fontWeight = FontWeight.Medium,
//                            color = if (currentRoute == item.route) Grey800 else Grey300
//                        )
//                    )
//                }
//            })
//        }
//    }
//}
//
//
//@Composable
//fun MainNavigationScreen(
//    navController: NavHostController,
//    scaffoldState: ScaffoldState
//) {
//    val signUpViewModel = viewModel<SignUpViewModel>() // ?
//
//    val slideEnterTransition = slideInHorizontally(
//        initialOffsetX = { -it },
//        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
//    )
//
//    val slideExitTransition = slideOutHorizontally(
//        targetOffsetX = { -it },
//        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
//    )
//
//    NavHost(navController = navController, startDestination = UserTypeNav.routeWithArgName()) {
//        composable(
//            route = SignInNav.route,
//        ) {
//            // SignInScreen(navHostController = navController)
//        }
//
//        composable(
//            route = UserTypeNav.routeWithArgName(),
//            arguments = UserTypeNav.arguments,
//            enterTransition = { slideEnterTransition },
//            exitTransition = { slideExitTransition }
//        ) {
//            /*
//            val signUpInfo = UserTypeNav.findArgument(it)
//            val accessToken = Uri.decode(signUpInfo?.accessToken)
//
//            UserTypeRoute(
//                onUserTypeSelect = { NavigationUtils.navigate(navController, LocationSearchNav.route) },
//                onBackButtonClick = { navController.popBackStack() },
//                signUpInfo = signUpInfo?.copy(accessToken = accessToken),
//                viewModel = signUpViewModel
//            )
//             */
//
//        }
//
//        composable(
//            route = LocationSearchNav.route,
//            enterTransition = { slideEnterTransition },
//            exitTransition = { slideExitTransition }
//        ) {
//            LocationRoute(
//                onSignUpSuccess = { NavigationUtils.navigate(navController, MainNav.Home.route) },
//                onBackButtonClick = { navController.popBackStack() },
//                viewModel = signUpViewModel
//            )
//        }
//
//        composable(
//            route = MainNav.Home.route
//        ) {
//            // MainHomeScreen(navController = navController)
//        }
//    }
//
//}
//
