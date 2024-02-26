package com.mommydndn.app.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.usecase.user.UserNotFoundException
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.usecase.user.GoogleAuthCodeNullException
import com.mommydndn.app.domain.usecase.user.SignInWithGoogleUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithKakaoUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithNaverUseCase
import com.mommydndn.app.domain.usecase.user.AccessTokenNullException
import com.mommydndn.app.utils.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface SignInParams

data class KakaoSignInParams(val accessToken: String? = null) : SignInParams
data class GoogleSignInParams(val authCode: String? = null) : SignInParams
data class NaverSignInParams(val accessToken: String? = null) : SignInParams

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithKakaoUseCase: SignInWithKakaoUseCase,
    private val signInWithNaverUseCase: SignInWithNaverUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
) : ViewModel() {

    private val signInExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = when (exception) {
            is UserNotFoundException -> {
                SignInUiState.NotSignedUpYet(
                    exception.accessToken,
                    exception.oAuthProvider
                )
            }

            is AccessTokenNullException -> SignInUiState.NotSignedInYet(errorMessage = "토큰을 찾을 수 없습니다.")
            is GoogleAuthCodeNullException -> SignInUiState.NotSignedInYet(errorMessage = "인증 코드를 찾을 수 없습니다.")
            else -> SignInUiState.NotSignedInYet(errorMessage = "로그인에 실패했습니다.")
        }
    }

    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.NotSignedInYet())
    val uiState: StateFlow<SignInUiState> = _uiState

    fun signIn(params: SignInParams) {
        _uiState.value = SignInUiState.NotSignedInYet(isLoading = true)
        viewModelScope.launch(signInExceptionHandler) {
            _uiState.value = when (params) {
                is KakaoSignInParams -> params.accessToken?.let { signInWithKakaoUseCase(it) }
                    ?: throw AccessTokenNullException()

                is NaverSignInParams -> params.accessToken?.let { signInWithNaverUseCase(it) }
                    ?: throw AccessTokenNullException()

                is GoogleSignInParams -> params.authCode?.let { signInWithGoogleUseCase(it) }
                    ?: throw GoogleAuthCodeNullException()
            }.let { result ->
                when (result) {
                    is Result.Success -> SignInUiState.Success
                    is Result.Loading -> SignInUiState.NotSignedInYet(isLoading = true)
                    is Result.Failure -> throw result.exception
                }
            }
        }
    }
}

sealed interface SignInUiState {

    data class NotSignedInYet(
        val isLoading: Boolean = false,
        val errorMessage: String = ""
    ) : SignInUiState

    data object Success : SignInUiState

    data class NotSignedUpYet(val accessToken: String, val oAuthProvider: OAuthProvider) :
        SignInUiState

}


