package com.mommydndn.app.ui.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.mommydndn.app.ui.components.common.Header
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.mommydndn.app.ui.components.box.SocialLoginBox
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Paddings
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.viewmodel.SignInViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.mommydndn.app.data.model.OAuthType
import com.mommydndn.app.ui.theme.Salmon600
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navHostController: NavHostController,
    googleSignInClient: GoogleSignInClient
) {
    val TAG = "SignInScreen"
    val context = LocalContext.current

    val signInIntent = googleSignInClient.signInIntent

    val startForResult =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            Log.e(TAG, result.resultCode.toString())
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)

                viewModel.handleGoogleSignInResult(
                    task,
                    navHostController
                )

            }
        }

    val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        when {
            error != null -> {
                Log.e("Kakao", "카카오 계정 로그인 실패", error)
            }

            token != null -> {
                loginWithKakaoNickName(token, viewModel, navHostController)
            }
        }
    }
    val naverCallback = object : OAuthLoginCallback {
        override fun onSuccess() {
            Log.e(TAG, "로그인 성공")

            val token = NaverIdLoginSDK.getAccessToken()
            if (token != null) {
                viewModel.signIn(tokenId = token, type = OAuthType.NAVER, navHostController)
            }
        }

        override fun onFailure(httpStatus: Int, message: String) {
            val errorCode = NaverIdLoginSDK.getLastErrorCode().code
            val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
            Log.e(TAG, "errorCode:$errorCode errorDesc:$errorDescription")
        }

        override fun onError(errorCode: Int, message: String) {
            onFailure(errorCode, message)
        }
    }
    Scaffold(topBar = {
        Header(
            rightContent = {
                TextButton(
                    onClick = {},
                    contentPadding = PaddingValues(
                        top = 6.dp,
                        bottom = 6.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(0.dp),
                        text = "먼저 둘러보기",
                        style = MaterialTheme.typography.paragraph300.copy(
                            color = Grey500,
                            fontWeight = FontWeight.Medium,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        )
                    )
                }
            }
        )
    }, bottomBar = {
        SocialLoginBox(
            modifier = Modifier.padding(bottom = 96.dp),
            onClickGoogle = {
                startForResult.launch(signInIntent)
            },
            onClickKakao = { loginKakao(context, kakaoCallback) },
            onClickNaver = { loginNaver(context, naverCallback) }
        )
    }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .width(125.dp)
                    .height(121.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(Paddings.extra))
            Text(
                text = "우리 동네 엄마 찾기",
                style = MaterialTheme.typography.heading800.copy(
                    color = Salmon600,
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(modifier = Modifier.height(Paddings.large))
            Text(
                text = "내 아이를 맡길 수 있는\n따뜻한 동네 엄마를 찾아보세요",
                style = MaterialTheme.typography.paragraph300.copy(
                    color = Grey500,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}


private fun loginWithKakaoNickName(token: OAuthToken, viewModel: SignInViewModel, navHostController: NavHostController) {
    UserApiClient.instance.me { user, error ->
        when {
            error != null -> {
                Log.e("Kakao", "사용자 정보 실패", error)
            }

            user != null -> {
                viewModel.signIn(
                    tokenId = token.accessToken,
                    type = OAuthType.KAKAO,
                    navHostController
                )
            }
        }
    }
}

private fun loginKakao(context: Context, kakaoCallback: (OAuthToken?, Throwable?) -> Unit) {
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        // 카카오 설치
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.e("Kakao", "카카오톡 로그인 실패", error)
            }

            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                return@loginWithKakaoTalk
            }

            UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
        }
    } else {
        // 카카오 미설치
        UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
    }
}

private fun loginNaver(context: Context, oAuthLoginCallback: OAuthLoginCallback) {
    NaverIdLoginSDK.authenticate(context, oAuthLoginCallback)
}

