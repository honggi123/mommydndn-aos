package com.mommydndn.app.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.usecase.user.UserNotFoundException
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.usecase.user.AuthCodeNullException
import com.mommydndn.app.domain.usecase.user.SignInException
import com.mommydndn.app.domain.usecase.user.SignInWithGoogleUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithKakaoUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithNaverUseCase
import com.mommydndn.app.domain.usecase.user.TokenNullException
import com.mommydndn.app.utils.result.Result
import com.mommydndn.app.utils.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.NullPointerException
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

            is TokenNullException -> SignInUiState.Failure(errorMessage = "토큰을 찾을 수 없습니다.")
            else -> SignInUiState.Failure(errorMessage = "로그인에 실패했습니다.")
        }
    }

    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.Loading)
    val uiState: StateFlow<SignInUiState> = _uiState

    fun signIn(params: SignInParams) {
        viewModelScope.launch(signInExceptionHandler) {
            _uiState.value = if (checkSignInParamsValid(params)) {
                when (params) {
                    is KakaoSignInParams -> signInWithKakaoUseCase(params.accessToken!!)
                    is NaverSignInParams -> signInWithNaverUseCase(params.accessToken!!)
                    is GoogleSignInParams -> signInWithGoogleUseCase(params.authCode!!)
                }.let { result ->
                    when (result) {
                        is Result.Success -> SignInUiState.Success
                        is Result.Loading -> SignInUiState.Loading
                        is Result.Failure -> throw result.exception
                    }
                }
            } else {
                throw NullPointerException()
            }
        }
    }

    private fun checkSignInParamsValid(params: SignInParams): Boolean {
        return when (params) {
            is KakaoSignInParams -> params.accessToken != null
            is NaverSignInParams -> params.accessToken != null
            is GoogleSignInParams -> params.authCode != null
        }
    }
}

sealed interface SignInUiState {

    data object Loading : SignInUiState

    data object Success : SignInUiState

    data class Failure(val errorMessage: String) : SignInUiState

    data class NotSignedUpYet(val accessToken: String, val oAuthProvider: OAuthProvider) :
        SignInUiState

}


