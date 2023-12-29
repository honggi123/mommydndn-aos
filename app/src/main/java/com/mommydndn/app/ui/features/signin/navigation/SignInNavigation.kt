package com.mommydndn.app.ui.features.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val SIGN_IN_ROUTE = "/sign_in"

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) {
    navigate(SIGN_IN_ROUTE, navOptions)
}

fun NavGraphBuilder.signInScreen(
    onExploreClick: () -> Unit,
    onSignInSuccess: () -> Unit,
) {
    composable(route = SIGN_IN_ROUTE) {

    }
}