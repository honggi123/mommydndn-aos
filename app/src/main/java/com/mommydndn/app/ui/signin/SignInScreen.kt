package com.mommydndn.app.ui.signin

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.feature.signin.components.SignInTopAppBar
import com.mommydndn.app.feature.signin.components.SocialLogin
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.ui.theme.paragraph300
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback

// TODO: sign_in -> sign_up 플로우 정리
@Composable
internal fun SignInScreen(
    onExploreClick: () -> Unit,
    onSignInSuccess: () -> Unit,
    // TODO
    onSignUpNeeded: (accessToken: String, oAuthProvider: OAuthProvider) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val launcherForActivityResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            GoogleSignIn.getSignedInAccountFromIntent(result.data)
                .addOnSuccessListener { account ->
                    account.serverAuthCode?.let { serverAuthCode ->
                        viewModel.signInWithGoogle(serverAuthCode)
                    }
                }.addOnFailureListener {
                    // todo: crashlytics_report
                }
        } else {
            // todo
        }
    }

    val onSocialLoginClick: (OAuthProvider) -> Unit = { oAuthProvider ->
        when (oAuthProvider) {
            OAuthProvider.NAVER -> {
                NaverIdLoginSDK.authenticate(context, object : OAuthLoginCallback {
                    override fun onSuccess() {
                        NaverIdLoginSDK.getAccessToken()?.let { accessToken ->
                            viewModel.signIn(OAuthProvider.NAVER, accessToken)
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
            OAuthProvider.KAKAO -> {
                val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                    if (token != null) {
                        token.accessToken.let { accessToken ->
                            viewModel.signIn(OAuthProvider.KAKAO, accessToken)
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
            OAuthProvider.GOOGLE -> {
                val options =
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestServerAuthCode(BuildConfig.GOOGLE_CLIENT_ID)
                        .build()

                val client = GoogleSignIn.getClient(context, options)

                launcherForActivityResult.launch(client.signInIntent)
            }
        }
    }

    SignInContent(
        onExploreClick = onExploreClick,
        onSocialLoginClick = onSocialLoginClick,
        modifier = modifier,
    )
}

@Composable
internal fun SignInContent(
    onExploreClick: () -> Unit,
    onSocialLoginClick: (OAuthProvider) -> Unit,
    modifier: Modifier = Modifier,
) {
    // todo: column?
    Column(modifier = modifier) {
        SignInTopAppBar(
            onExploreClick = onExploreClick,
            modifier = Modifier.fillMaxWidth(),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_logo),
                contentDescription = "SignInScreen_Logo",
                modifier = Modifier.size(124.dp),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.find_babysitters_near_me),
                color = Salmon600,
                style = MaterialTheme.typography.heading800.merge(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = R.string.find_babysitters_near_me_description),
                color = Grey500,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.paragraph300.merge(
                    fontWeight = FontWeight.Normal,
                ),
            )
        }

        SocialLogin(
            onClick = onSocialLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 96.dp),
        )
    }
}

@Preview
@Composable
private fun SignInScreen_Preview() {
    SignInContent(
        onExploreClick = {},
        onSocialLoginClick = {},
        modifier = Modifier
            .background(White)
            .fillMaxSize(),
    )
}