package com.mommydndn.app.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.usecase.user.GoogleAuthCode
import com.mommydndn.app.domain.usecase.user.SignInWithGoogleUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithKakaoUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithNaverUseCase
import com.mommydndn.app.utils.result.Result
import com.mommydndn.app.utils.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithKakaoUseCase: SignInWithKakaoUseCase,
    private val signInWithNaverUseCase: SignInWithNaverUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<SignInUiState> =
        MutableStateFlow(SignInUiState.LoadSuccess)
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun signInWithAuthCode(
        oauthProvider: OAuthProvider,
        authCode: String?
    ) {
        viewModelScope.launch {
            signInWithGoogle(authCode).let { result ->
                val uiState = when (result) {
                    is Result.Loading -> SignInUiState.SignInLoading
                    is Result.Success -> SignInUiState.SignInSuccess
                    is Result.Failure -> {
                        val exception = result.exception
                        when {
                            exception is HttpException && exception.code() == 403 ->
                                // SignInUiState.NotSignedUpYet(socialToken, oauthProvider)
                                TODO()

                            else ->
                                SignInUiState.SignInFailure(exception)
                        }
                    }
                }
                _uiState.emit(uiState)
            }
        }
    }

    fun signInWithSocialToken(
        oauthProvider: OAuthProvider,
        socialToken: String?,
    ) {
        viewModelScope.launch {
            when (oauthProvider) {
                OAuthProvider.Naver -> signInWithNaver(socialToken)
                OAuthProvider.Kakao -> signInWithKakao(socialToken)
                else -> Result.Failure(Exception("not need token"))
            }.let { result ->
                val uiState = when (result) {
                    is Result.Loading -> SignInUiState.SignInLoading
                    is Result.Success -> SignInUiState.SignInSuccess
                    is Result.Failure -> {
                        val exception = result.exception
                        when {
                            exception is HttpException && exception.code() == 403 ->
                                SignInUiState.NotSignedUpYet(socialToken!!, oauthProvider)

                            else ->
                                SignInUiState.SignInFailure(exception)
                        }
                    }
                }
                _uiState.emit(uiState)
            }
        }
    }

    private suspend fun signInWithKakao(token: String?): Result<Unit> {
        return if (token != null) {
            signInWithKakaoUseCase(token)
        } else {
            Result.Failure(Exception(message = "token null"))
        }
    }

    private suspend fun signInWithNaver(token: String?): Result<Unit> {
        return if (token != null) {
            signInWithNaverUseCase(token)
        } else {
            Result.Failure(Exception(message = "token null"))
        }
    }

    private suspend fun signInWithGoogle(authCode: String?): Result<Unit> {
        return if (authCode != null) {
            signInWithGoogleUseCase(authCode)
        } else {
            Result.Failure(Exception(message = "authCode null"))
        }
    }
}

sealed interface SignInUiState {

    data object LoadSuccess : SignInUiState

    data object SignInLoading : SignInUiState

    data class SignInFailure(val exception: Exception) : SignInUiState

    data object SignInSuccess : SignInUiState

    data class NotSignedUpYet(val accessToken: String, val oAuthProvider: OAuthProvider) :
        SignInUiState

}