@file:OptIn(ExperimentalAnimationApi::class)

package com.mommydndn.app

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.mommydndn.app.ui.SignInNav
import com.mommydndn.app.ui.TownCheckNav
import com.mommydndn.app.ui.TypeChoiceNav
import com.mommydndn.app.ui.signIn.SignInScreen
import com.mommydndn.app.ui.signUp.TownCheckScreen
import com.mommydndn.app.ui.signUp.TypeChoiceScreen
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MommydndnaosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    MainNavigationScreen(googleSignInClient)
                }
            }
        }
    }
}

@Composable
fun MainNavigationScreen(googleSignInClient: GoogleSignInClient) {
    val navController = rememberNavController()
    val slideEnterTransition = slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    val slideExitTransition = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    NavHost(navController = navController, startDestination = TownCheckNav.route) {
        composable(
            route = SignInNav.route,
        ) {
            SignInScreen(navHostController = navController, googleSignInClient = googleSignInClient)
        }
        composable(
            route = TypeChoiceNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            TypeChoiceScreen(navHostController = navController)
        }
        composable(
            route = TownCheckNav.route,
            enterTransition = { slideEnterTransition },
            exitTransition = { slideExitTransition }
        ) {
            TownCheckScreen(navHostController = navController)
        }
    }
}

