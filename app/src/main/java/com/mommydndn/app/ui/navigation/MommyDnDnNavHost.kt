package com.mommydndn.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mommydndn.app.ui.features.signin.navigation.SIGN_IN_ROUTE

@Composable
internal fun MommyDnDnNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SIGN_IN_ROUTE,
        modifier = modifier,
    ) {
        composable(SIGN_IN_ROUTE) {

        }
    }
}