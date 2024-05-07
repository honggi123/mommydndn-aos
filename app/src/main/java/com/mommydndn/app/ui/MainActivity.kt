package com.mommydndn.app.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mommydndn.app.ui.signin.navigation.SIGN_IN_ROUTE
import com.mommydndn.app.ui.signin.navigation.signInScreen
import com.mommydndn.app.ui.theme.MommydndnTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // TODO: POST_NOTIFICATION PERMISSION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MommydndnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    MmdndnNavHost()
                }
            }
        }
    }
}

// test
@Composable
fun MmdndnNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SIGN_IN_ROUTE,
        modifier = Modifier.fillMaxSize(),
    ) {
        signInScreen(
            onExploreClick = {},
            onSignInSuccess = {},
            onSignUpNeeded = { accessToken, oAuthProvider -> }
        )
    }
}

