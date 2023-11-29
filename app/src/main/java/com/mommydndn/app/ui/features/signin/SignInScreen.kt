package com.mommydndn.app.ui.features.signin

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.ui.theme.paragraph300
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import kotlinx.coroutines.Dispatchers

@Composable
internal fun SignInRoute(
    onExploreClick: () -> Unit,
    onSignInSuccess: () -> Unit,
    onSignUpNeeded: (accessToken: String, oAuthProvider: OAuthProvider) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val launcherForActivityResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    .addOnSuccessListener { account ->
                        account.serverAuthCode?.let { serverAuthCode ->
                            viewModel.signInWithGoogle(serverAuthCode)
                        }
                    }
                    .addOnFailureListener {
                        // todo: crashlytics_report
                    }
            }
        }

    val onSignInClick: (OAuthProvider) -> Unit = { socialLoginProvider ->
        when (socialLoginProvider) {
            OAuthProvider.NAVER -> {
                NaverIdLoginSDK.authenticate(context, object : OAuthLoginCallback {
                    override fun onSuccess() {
                        NaverIdLoginSDK.getAccessToken()?.let { accessToken ->
                            viewModel.signIn(accessToken, socialLoginProvider)
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
                val callback: (token: OAuthToken?, error: Throwable?) -> Unit = { token, error ->
                    if (error != null) {
                        if (error !is ClientError || error.reason != ClientErrorCause.Cancelled) {
                            // todo: crashlytics_report
                        }
                    } else {
                        token?.accessToken?.let { accessToken ->
                            viewModel.signIn(accessToken, socialLoginProvider)
                        }
                    }
                }
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                }
            }

            OAuthProvider.GOOGLE -> {
                val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestServerAuthCode(BuildConfig.GOOGLE_CLIENT_ID)
                    .build()

                with(GoogleSignIn.getClient(context, options)) {
                    launcherForActivityResult.launch(signInIntent)
                }
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        SignInUiState.Loading -> SignInScreen(
            onExploreClick = onExploreClick,
            onSignInClick = onSignInClick,
            modifier = modifier,
        )

        SignInUiState.Success -> onSignInSuccess()
        is SignInUiState.NotSignedUpYet -> with(state) {
            onSignUpNeeded(accessToken, oAuthProvider)
        }

        is SignInUiState.Failure -> {
            // todo
        }
    }
}

@Composable
private fun SignInScreen(
    onExploreClick: () -> Unit,
    onSignInClick: (OAuthProvider) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SignInTopAppBar(onExploreClick)
        },
        bottomBar = {
            SocialLogin(onSignInClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.width(125.dp),
                painter = painterResource(id = R.drawable.icon_logo),
                contentDescription = "LogoImage"
            )

            Spacer(modifier = Modifier)

            Text(
                text = stringResource(id = R.string.find_babysitters_near_me),
                style = MaterialTheme.typography.heading800.copy(
                    color = Salmon600,
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = stringResource(id = R.string.find_babysitters_near_me_description),
                style = MaterialTheme.typography.paragraph300.copy(
                    color = Grey500,
                    fontWeight = FontWeight.Medium,
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun SignInTopAppBar(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Header(
        modifier = modifier,
        rightContent = {
            TextButton(
                onClick = onExploreClick, contentPadding = PaddingValues(
                    top = 6.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 6.dp,
                )
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.explore),
                    style = MaterialTheme.typography.paragraph300.copy(
                        color = Grey500,
                        fontWeight = FontWeight.Medium,
                    )
                )
            }
        }
    )
}

@Composable
private fun SocialLogin(
    onClick: (OAuthProvider) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 96.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.social_login),
            style = MaterialTheme.typography.paragraph300.copy(
                color = Grey700,
                fontWeight = FontWeight.Medium,
            ),
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SocialLoginIconButton(
                onClick = { onClick(OAuthProvider.NAVER) },
                iconResource = painterResource(id = R.drawable.icon_naver)
            )

            SocialLoginIconButton(
                onClick = { onClick(OAuthProvider.KAKAO) },
                iconResource = painterResource(id = R.drawable.icon_kakao)
            )

            SocialLoginIconButton(
                onClick = { onClick(OAuthProvider.GOOGLE) },
                iconResource = painterResource(id = R.drawable.icon_google)
            )
        }
    }
}

@Composable
private fun SocialLoginIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconResource: Painter,
    contentDescription: String = "SocialLoginIconButton",
) {
    IconButton(onClick = onClick) {
        Image(
            painter = iconResource, contentDescription = contentDescription, modifier = modifier
        )
    }
}