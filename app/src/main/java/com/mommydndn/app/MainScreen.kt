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
import androidx.compose.runtime.remember
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
import com.google.android.gms.location.FusedLocationProviderClient
import com.mommydndn.app.ui.features.care.CareScreen
import com.mommydndn.app.ui.features.care.company.write.CompanyLocationSearchScreen
import com.mommydndn.app.ui.features.care.company.write.CompanyWriteScreen
import com.mommydndn.app.ui.features.care.company.write.CompanyWriteViewModel
import com.mommydndn.app.ui.features.care.company.write.preview.CompanyPreviewScreen
import com.mommydndn.app.ui.features.care.joboffer.write.JobOfferLocationSearchScreen
import com.mommydndn.app.ui.features.care.joboffer.write.JobOfferWriteScreen
import com.mommydndn.app.ui.features.care.joboffer.write.JobOfferWriteViewModel
import com.mommydndn.app.ui.features.care.joboffer.write.preview.JobOfferPreviewScreen
import com.mommydndn.app.ui.features.care.jobseeker.write.JobSeekerLocationSearchScreen
import com.mommydndn.app.ui.features.care.jobseeker.write.JobSeekerWriteScreen
import com.mommydndn.app.ui.features.care.jobseeker.write.JobSeekerWriteViewModel
import com.mommydndn.app.ui.features.care.jobseeker.write.preview.JobSeekerPreviewScreen
import com.mommydndn.app.ui.features.home.MainHomeScreen
import com.mommydndn.app.ui.features.signin.SignInScreen
import com.mommydndn.app.ui.features.signup.LocationSearchRoute
import com.mommydndn.app.ui.features.signup.LocationSearchScreen
import com.mommydndn.app.ui.features.signup.SelectUserTypeRoute
import com.mommydndn.app.ui.features.signup.SignUpViewModel
import com.mommydndn.app.ui.navigation.CompanyLocationSearchNav
import com.mommydndn.app.ui.navigation.CompanyWriteNav
import com.mommydndn.app.ui.navigation.CompanyWritePreviewNav
import com.mommydndn.app.ui.navigation.JobOfferLocationSearchNav
import com.mommydndn.app.ui.navigation.JobOfferWriteNav
import com.mommydndn.app.ui.navigation.JobOfferWritePreviewNav
import com.mommydndn.app.ui.navigation.JobSeekerLocationSearchNav
import com.mommydndn.app.ui.navigation.JobSeekerWriteNav
import com.mommydndn.app.ui.navigation.JobSeekerWritePreviewNav
import com.mommydndn.app.ui.navigation.LocationSearchNav
import com.mommydndn.app.ui.navigation.MainNav
import com.mommydndn.app.ui.navigation.SignInNav
import com.mommydndn.app.ui.navigation.UserTypeNav
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.util.NavigationUtils

@Composable
internal fun MainScreen(fusedLocationClient: FusedLocationProviderClient) {
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
            fusedLocationClient,
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
    fusedLocationClient: FusedLocationProviderClient,
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

    NavHost(navController = navController, startDestination = SignInNav.route) {
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

            SelectUserTypeRoute(
                navigateToNextScreen = { NavigationUtils.navigate(navController, LocationSearchNav.route) },
                navigateToPreviousScreen = { navController.popBackStack() },
                signUpInfo = signUpInfo?.copy(accessToken = accessToken),
                viewModel = signUpViewModel
            )
        }

        composable(
            route = LocationSearchNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            LocationSearchRoute(
                navigateToNextScreen = {},
                navigateToPreviousScreen = { navController.popBackStack() },
                fusedLocationClient = fusedLocationClient,
                viewModel = signUpViewModel
            )
        }

        composable(
            route = MainNav.Home.route
        ) {
            MainHomeScreen(navController = navController)
        }

        composable(
            route = MainNav.Care.route
        ) {
            CareScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }

        composable(
            route = JobOfferWriteNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) { backStackEntry ->
            val JobOfferWriteStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(JobOfferWriteNav.route)
            }
            val jobOfferWriteViewModel =
                hiltViewModel<JobOfferWriteViewModel>(JobOfferWriteStackEntry)
            JobOfferWriteScreen(
                navController = navController,
                viewModel = jobOfferWriteViewModel,
                scaffoldState = scaffoldState
            )
        }

        composable(
            route = JobOfferLocationSearchNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) { backStackEntry ->
            val JobOfferWriteStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(JobOfferWriteNav.route)
            }
            val jobOfferWriteViewModel =
                hiltViewModel<JobOfferWriteViewModel>(JobOfferWriteStackEntry)
            JobOfferLocationSearchScreen(
                navController = navController,
                viewModel = jobOfferWriteViewModel
            )
        }

        composable(
            route = JobOfferWritePreviewNav.routeWithArgName(),
            arguments = JobOfferWritePreviewNav.arguments,

            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            val jobOfferPreview = JobOfferWritePreviewNav.findArgument(it)

            JobOfferPreviewScreen(
                jobOfferPreview = jobOfferPreview, navController = navController
            )
        }

        composable(
            route = JobSeekerWriteNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) { backStackEntry ->
            val JobSeekerWriteStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(JobSeekerWriteNav.route)
            }

            val jobSeekerWriteViewModel =
                hiltViewModel<JobSeekerWriteViewModel>(JobSeekerWriteStackEntry)

            JobSeekerWriteScreen(
                navController = navController,
                viewModel = jobSeekerWriteViewModel,
                scaffoldState = scaffoldState
            )
        }

        composable(
            route = JobSeekerWriteNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) { backStackEntry ->
            val JobSeekerWriteStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(JobSeekerWriteNav.route)
            }

            val jobSeekerWriteViewModel =
                hiltViewModel<JobSeekerWriteViewModel>(JobSeekerWriteStackEntry)

            JobSeekerWriteScreen(
                navController = navController,
                viewModel = jobSeekerWriteViewModel,
                scaffoldState = scaffoldState
            )
        }

        composable(
            route = JobSeekerWritePreviewNav.routeWithArgName(),
            arguments = JobSeekerWritePreviewNav.arguments,

            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            val jobSeekerPreview = JobSeekerWritePreviewNav.findArgument(it)

            JobSeekerPreviewScreen(
                jobSeekerPreview = jobSeekerPreview, navController = navController
            )
        }

        composable(
            route = JobSeekerLocationSearchNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) { backStackEntry ->
            val JobSeekerWriteStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(JobSeekerWriteNav.route)
            }

            val jobSeekerWriteViewModel =
                hiltViewModel<JobSeekerWriteViewModel>(JobSeekerWriteStackEntry)

            JobSeekerLocationSearchScreen(
                navController = navController,
                viewModel = jobSeekerWriteViewModel
            )
        }

        composable(
            route = CompanyWriteNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) { backStackEntry ->
            val CompanyWriteNavStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(CompanyWriteNav.route)
            }

            val companyWriteViewModel =
                hiltViewModel<CompanyWriteViewModel>(CompanyWriteNavStackEntry)

            CompanyWriteScreen(
                navController = navController,
                viewModel = companyWriteViewModel,
                scaffoldState = scaffoldState
            )
        }

        composable(
            route = CompanyLocationSearchNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) { backStackEntry ->
            val CompanyWriteStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(CompanyWriteNav.route)
            }

            val companyWriteViewModel =
                hiltViewModel<CompanyWriteViewModel>(CompanyWriteStackEntry)

            CompanyLocationSearchScreen(
                navController = navController,
                viewModel = companyWriteViewModel
            )
        }

        composable(
            route = CompanyWritePreviewNav.routeWithArgName(),
            arguments = CompanyWritePreviewNav.arguments,

            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            val companyPreview = CompanyWritePreviewNav.findArgument(it)

            CompanyPreviewScreen(
                companyPreiew = companyPreview, navController = navController
            )
        }

    }

}

