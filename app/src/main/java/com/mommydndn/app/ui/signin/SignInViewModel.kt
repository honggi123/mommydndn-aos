package com.mommydndn.app.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.usecase.user.UserNotFoundException
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.usecase.user.SignInWithGoogleUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithKakaoUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithNaverUseCase
import com.mommydndn.app.domain.usecase.user.TokenNullException
import com.mommydndn.app.utils.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithKakaoUseCase: SignInWithKakaoUseCase,
    private val signInWithNaverUseCase: SignInWithNaverUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState.Success())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun signIn(
        oauthProvider: OAuthProvider,
        socialToken: String? = null,
        authCode: String? = null
    ) {
        viewModelScope.launch {
            when (oauthProvider) {
                OAuthProvider.Naver -> signInWithNaverUseCase(socialToken)
                OAuthProvider.Kakao -> signInWithKakaoUseCase(socialToken)
                OAuthProvider.Google -> signInWithGoogleUseCase(authCode)
            }.let { result ->
                val uiState = when (result) {
                    is Result.Loading -> SignInUiState.SignInLoading
                    is Result.Success -> SignInUiState.Success(isSignInSuccessful = true)
                    is Result.Failure -> {
                        val exception = result.exception
                        when (exception) {
                            is UserNotFoundException ->
                                SignInUiState.NotSignedUpYet(exception.token, oauthProvider)

                            is TokenNullException ->
                                SignInUiState.SignInFailure(errorMessage = "토큰을 찾을 수 없습니다.")

                            else ->
                                SignInUiState.SignInFailure(errorMessage = "로그인에 실패했습니다.")
                        }
                    }
                }
                _uiState.emit(uiState)
            }
        }
    }
}

sealed interface SignInUiState {

    data class Success(
        val isSignInSuccessful: Boolean = false
    ) : SignInUiState

    data object SignInLoading : SignInUiState

    data class SignInFailure(val errorMessage: String) : SignInUiState

    data class NotSignedUpYet(val accessToken: String, val oAuthProvider: OAuthProvider) :
        SignInUiState

}