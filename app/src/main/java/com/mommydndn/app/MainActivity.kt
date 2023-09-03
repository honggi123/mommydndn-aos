package com.mommydndn.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kakao.sdk.common.KakaoSdk
import com.mommydndn.app.ui.LoginScreen
import com.mommydndn.app.ui.MemberTypeChoiceNav
import com.mommydndn.app.ui.RegistrationScreen
import com.mommydndn.app.ui.SignInNav
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.navercorp.nid.NaverIdLoginSDK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MommydndnaosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    MainNavigationScreen()
                }
            }
        }
    }
}

@Composable
fun MainNavigationScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SignInNav.route) {
        composable(
            route = SignInNav.route,
        ) {
            LoginScreen(navHostController = navController)
        }
        composable(
            route = MemberTypeChoiceNav.route,
        ) {
            RegistrationScreen(navHostController = navController)
        }
    }
}

