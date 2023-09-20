@file:OptIn(ExperimentalAnimationApi::class)

package com.mommydndn.app

import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.mommydndn.app.data.model.OAuthType
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.data.model.UserType
import com.mommydndn.app.ui.SignInNav
import com.mommydndn.app.ui.TownCheckNav
import com.mommydndn.app.ui.TypeChoiceNav
import com.mommydndn.app.ui.signIn.SignInScreen
import com.mommydndn.app.ui.signUp.TownCheckScreen
import com.mommydndn.app.ui.signUp.TypeChoiceScreen
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            MommydndnaosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    MainNavigationScreen(googleSignInClient, fusedLocationClient)
                }
            }
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
            TypeChoiceScreen(
                signUpInfo = signUpInfo,
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
    }
}

