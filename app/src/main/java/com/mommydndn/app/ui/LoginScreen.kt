package com.mommydndn.app.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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
import com.mommydndn.app.ui.component.Header
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.mommydndn.app.ui.component.SocialLoginBox
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Paddings
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.viewmodel.AccountViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kakao.sdk.common.util.Utility
import com.mommydndn.app.data.model.LoginType
import com.mommydndn.app.ui.theme.Salmon600
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: AccountViewModel = viewModel(),
    navHostController: NavHostController
) {
    val TAG = "LoginScreen"
    val context = LocalContext.current
    val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        when {
            error != null -> {
                Log.e("Kakao", "카카오 계정 로그인 실패", error)
            }

            token != null -> {
                loginWithKakaoNickName(token, viewModel)
            }
        }
    }
    val naverCallback = object : OAuthLoginCallback {
        override fun onSuccess() {
            Log.e(TAG, "로그인 성공")

            val token = NaverIdLoginSDK.getAccessToken()
            if (token != null) {
                viewModel.signIn(tokenId = token, LoginType.NAVER)
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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
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

        SocialLoginBox(
            modifier = Modifier.padding(bottom = 96.dp),
            onClickGoogle = { loginGoogle() },
            onClickKakao = { loginKakao(context, kakaoCallback) },
            onClickNaver = { loginNaver(context, naverCallback) }
        )
    }

}


private fun loginWithKakaoNickName(token: OAuthToken, viewModel: AccountViewModel) {
    UserApiClient.instance.me { user, error ->
        when {
            error != null -> {
                Log.e("Kakao", "사용자 정보 실패", error)
            }

            user != null -> {
                viewModel.signIn(
                    tokenId = token.accessToken,
                    type = LoginType.KAKAO
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

private fun loginGoogle() {}
private fun loginNaver(context: Context, oAuthLoginCallback: OAuthLoginCallback) {
    NaverIdLoginSDK.authenticate(context, oAuthLoginCallback)
}
