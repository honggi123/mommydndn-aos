package com.mommydndn.app.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.model.OAuthProvider
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

    fun signIn(
        oauthProvider: OAuthProvider,
        accessToken: String,
        googleAuthCode: String? = null
    ) {
        viewModelScope.launch {
            when (oauthProvider) {
                OAuthProvider.Naver -> signInWithNaverUseCase(accessToken)
                OAuthProvider.Kakao -> signInWithKakaoUseCase(accessToken)
                OAuthProvider.Google -> {
                    if (googleAuthCode != null) {
                        signInWithGoogleUseCase(googleAuthCode)
                    } else {
                        TODO()
                    }
                }
            }.let { result ->
                val uiState = when (result) {
                    is Result.Success -> SignInUiState.SignInSuccess
                    is Result.Loading -> SignInUiState.SignInLoading
                    is Result.Failure -> {
                        val exception = result.exception
                        when {
                            exception is HttpException && exception.code() == 403 ->
                                SignInUiState.NotSignedUpYet(accessToken, oauthProvider)

                            else ->
                                SignInUiState.SignInFailure(exception)
                        }
                    }
                }
                _uiState.emit(uiState)
            }
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