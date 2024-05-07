package com.mommydndn.app.ui.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.ui.signin.components.SignInTopAppBar
import com.mommydndn.app.ui.signin.components.SocialLogin
import com.mommydndn.app.ui.signin.components.WelcomeLogoWithMessage
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.White
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback

@Composable
internal fun SignInScreen(
    onExploreClick: () -> Unit,
    onSignInSuccess: () -> Unit,
    onSignUpNeeded: (accessToken: String, oAuthProvider: OAuthProvider) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val googleActivityResultLauncher =
        rememberLauncherForGoogleActivityResult(onAuthSuccess = viewModel::signIn)

    when (val state = uiState) {
        is SignInUiState.Success -> onSignInSuccess()
        is SignInUiState.NotSignedUpYet -> onSignUpNeeded(state.accessToken, state.oAuthProvider)
        is SignInUiState.NotSignedInYet -> {
            SignInScreen(
                onKakakSignInClick = {
                    if (!state.isLoading)
                        authenticateKakao(context = context, onAuthSuccess = viewModel::signIn)
                },
                onNaverSignInClick = {
                    if (!state.isLoading)
                        authenticateNaver(context = context, onAuthSuccess = viewModel::signIn)
                },
                onGoogleSignInClick = {
                    if (!state.isLoading)
                        getGoogleSignInIntent(context).let { intent ->
                            googleActivityResultLauncher.launch(intent)
                        }
                },
                onExploreClick = onExploreClick,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun rememberLauncherForGoogleActivityResult(
    onAuthSuccess: (GoogleSignInParams) -> Unit,
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            GoogleSignIn.getSignedInAccountFromIntent(result.data)
                .addOnSuccessListener { account ->
                    onAuthSuccess(GoogleSignInParams(account.serverAuthCode))
                }.addOnFailureListener {
                    // todo: crashlytics_report
                }
        } else {
            // todo
        }
    }
}


@Composable
private fun SignInScreen(
    onExploreClick: () -> Unit,
    onKakakSignInClick: () -> Unit,
    onNaverSignInClick: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            SignInTopAppBar(
                onExploreClick = onExploreClick,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        bottomBar = {
            SocialLogin(
                onKakakSignInClick = onKakakSignInClick,
                onNaverSignInClick = onNaverSignInClick,
                onGoogleSignInClick = onGoogleSignInClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 96.dp),
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeLogoWithMessage(
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}

private fun getGoogleSignInIntent(
    context: Context
): Intent {
    val options =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestServerAuthCode(BuildConfig.GOOGLE_CLIENT_ID)
            .build()

    val client = GoogleSignIn.getClient(context, options)

    return client.signInIntent
}

private fun authenticateNaver(
    context: Context,
    onAuthSuccess: (NaverSignInParams) -> Unit,
    onAuthFailure: () -> Unit = {}
) {
    NaverIdLoginSDK.authenticate(context, object : OAuthLoginCallback {
        override fun onSuccess() {
            NaverIdLoginSDK.getAccessToken().let { accessToken ->
                onAuthSuccess(NaverSignInParams(accessToken))
            }
        }

        override fun onFailure(httpStatus: Int, message: String) {
            onAuthFailure() // todo: crashlytics_report
        }

        override fun onError(errorCode: Int, message: String) {
            onFailure(errorCode, message)
        }
    })
}

private fun authenticateKakao(
    context: Context,
    onAuthSuccess: (KakaoSignInParams) -> Unit,
    onAuthFailure: () -> Unit = {}
) {
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (token != null) {
            onAuthSuccess(KakaoSignInParams(token.accessToken))
        } else {
            if (error !is ClientError || error.reason == ClientErrorCause.Cancelled) {
                onAuthFailure() // todo: crashlytics_report
            }
        }
    }

    with(UserApiClient.instance) {
        if (isKakaoTalkLoginAvailable(context)) {
            loginWithKakaoTalk(context, callback = callback)
        } else {
            loginWithKakaoAccount(context, callback = callback)
        }
    }
}

@Preview
@Composable
private fun SignInScreen_Preview() {
    MommydndnTheme {
        SignInScreen(
            onExploreClick = {},
            onKakakSignInClick = {},
            onNaverSignInClick = {},
            onGoogleSignInClick = {},
            modifier = Modifier
                .background(White)
                .fillMaxSize(),
        )
    }
}