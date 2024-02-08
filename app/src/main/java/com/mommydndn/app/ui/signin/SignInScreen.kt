package com.mommydndn.app.ui.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.ui.signin.components.LogoWithIntroduction
import com.mommydndn.app.ui.signin.components.SignInTopAppBar
import com.mommydndn.app.ui.signin.components.SocialLogin
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.ui.theme.paragraph300
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback

// TODO: sign_in -> sign_up 플로우 정리
@Composable
internal fun SignInScreenRoute(
    onExploreClick: () -> Unit,
    onSignInSuccess: () -> Unit,
    onSignUpNeeded: (accessToken: String, oAuthProvider: OAuthProvider) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val launcherForGoogleActivityResult = rememberLauncherForGoogleActivityResult(viewModel)

    val onSocialLoginClick: (OAuthProvider) -> Unit = { oAuthProvider ->
        when (oAuthProvider) {
            OAuthProvider.Naver -> authenticateWithNaver(
                context = context,
                onAuthSuccess = { viewModel.signIn(OAuthProvider.Naver, socialToken = it) })

            OAuthProvider.Kakao -> authenticateWithKakao(
                context = context,
                onAuthSuccess = { viewModel.signIn(OAuthProvider.Kakao, socialToken = it) })

            OAuthProvider.Google -> {
                val intent = getGoogleSignInIntent(context)
                launcherForGoogleActivityResult.launch(intent)
            }
        }
    }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (val uiState = uiState.value) {
        is SignInUiState.SignInFailure -> TODO()
        is SignInUiState.SignInSuccess -> {
            SignInScreen(
                onExploreClick = onExploreClick,
                onSocialLoginClick = onSocialLoginClick,
                modifier = modifier,
            )
        }

        is SignInUiState.NotSignedUpYet ->
            onSignUpNeeded(uiState.accessToken, uiState.oAuthProvider)

        is SignInUiState.SignInSuccess -> onSignInSuccess

        else -> TODO()
    }
}

@Composable
fun rememberLauncherForGoogleActivityResult(viewModel: SignInViewModel): ManagedActivityResultLauncher<Intent, ActivityResult> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            GoogleSignIn.getSignedInAccountFromIntent(result.data)
                .addOnSuccessListener { account ->
                    account.serverAuthCode.let { authCode ->
                        viewModel.signIn(OAuthProvider.Google, authCode = authCode)
                    }
                }.addOnFailureListener {
                    // todo: crashlytics_report
                }
        } else {
            // todo
        }
    }
}

@Composable
internal fun SignInScreen(
    onExploreClick: () -> Unit,
    onSocialLoginClick: (OAuthProvider) -> Unit,
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
                onClick = onSocialLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 96.dp),
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LogoWithIntroduction(
            modifier = modifier.padding(innerPadding).fillMaxSize()
        )
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

private fun authenticateWithNaver(
    context: Context,
    onAuthSuccess: (String?) -> Unit
) {
    NaverIdLoginSDK.authenticate(context, object : OAuthLoginCallback {
        override fun onSuccess() {
            NaverIdLoginSDK.getAccessToken().let { accessToken ->
                onAuthSuccess(accessToken)
            }
        }

        override fun onFailure(httpStatus: Int, message: String) {
            // todo: crashlytics_report
        }

        override fun onError(errorCode: Int, message: String) {
            onFailure(errorCode, message)
        }
    })
}

private fun authenticateWithKakao(
    context: Context,
    onAuthSuccess: (String?) -> Unit
) {
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (token != null) {
            token.accessToken.let { accessToken ->
                onAuthSuccess(accessToken)
            }
        } else {
            if (error !is ClientError || error.reason == ClientErrorCause.Cancelled) {
                // todo: crashlytics_report
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
            onSocialLoginClick = {},
            modifier = Modifier
                .background(White)
                .fillMaxSize(),
        )
    }
}