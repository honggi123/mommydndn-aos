package com.mommydndn.app.ui.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.ui.signin.SignInScreen

const val SIGN_IN_ROUTE = "signin_route"

fun NavController.navigateToSignIn(navOptions: NavOptions) = navigate(SIGN_IN_ROUTE, navOptions)

fun NavGraphBuilder.signInScreen(
    onExploreClick: () -> Unit,
    onSignUpNeeded: (accessToken: String, oAuthProvider: OAuthProvider) -> Unit,
    onSignInSuccess: () -> Unit
) {
    composable(
        route = SIGN_IN_ROUTE,
    ) {
        SignInScreen(
            onSignUpNeeded = onSignUpNeeded,
            onSignInSuccess = onSignInSuccess,
            onExploreClick = onExploreClick
        )
    }
}