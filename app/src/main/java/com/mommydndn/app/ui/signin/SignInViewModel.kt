package com.mommydndn.app.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.usecase.user.UserNotFoundException
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.usecase.user.AuthCodeNullException
import com.mommydndn.app.domain.usecase.user.SignInWithGoogleUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithKakaoUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithNaverUseCase
import com.mommydndn.app.domain.usecase.user.TokenNullException
import com.mommydndn.app.utils.result.Result
import com.mommydndn.app.utils.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface signInParams

data class KakaoSignInParams(val token: String? = null) : signInParams
data class GoogleSignInParams(val authCode: String? = null) : signInParams
data class NaverSignInParams(val token: String? = null) : signInParams

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithKakaoUseCase: SignInWithKakaoUseCase,
    private val signInWithNaverUseCase: SignInWithNaverUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
) : ViewModel() {

    private val signInParams = MutableStateFlow<signInParams?>(null)

    val uiState: StateFlow<SignInUiState> = signInParams
        .filterNotNull()
        .map { params ->
            when (params) {
                is KakaoSignInParams -> signInWithKakaoUseCase(params.token)
                is NaverSignInParams -> signInWithNaverUseCase(params.token)
                is GoogleSignInParams -> signInWithGoogleUseCase(params.authCode)
            }
        }.map { result ->
            when (result) {
                is Result.Success -> SignInUiState.SignInSuccess
                is Result.Failure -> {
                    val exception = result.exception
                    when (exception) {
                        is UserNotFoundException ->
                            SignInUiState.NotSignedUpYet(exception.token, exception.oAuthProvider)

                        is TokenNullException ->
                            SignInUiState.SignInFailure(errorMessage = "토큰을 찾을 수 없습니다.")

                        is AuthCodeNullException ->
                            SignInUiState.SignInFailure(errorMessage = "인증코드를 찾을 수 없습니다.")

                        else ->
                            SignInUiState.SignInFailure(errorMessage = "로그인에 실패했습니다.")

                    }
                }

                is Result.Loading -> {
                    SignInUiState.SignInLoading
                }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            SignInUiState.SignInLoading
        )

    fun signIn(
        signInParams: signInParams
    ) {
        this.signInParams.value = signInParams
    }
}

sealed interface SignInUiState {

    data object SignInLoading : SignInUiState

    data object SignInSuccess : SignInUiState

    data class SignInFailure(val errorMessage: String) : SignInUiState

    data class NotSignedUpYet(val accessToken: String, val oAuthProvider: OAuthProvider) :
        SignInUiState

}