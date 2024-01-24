package com.mommydndn.app.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.usecase.user.SignInParams
import com.mommydndn.app.domain.usecase.user.SignInUseCase
import com.mommydndn.app.domain.usecase.user.SignInWithGoogleParams
import com.mommydndn.app.domain.usecase.user.SignInWithGoogleUseCase
import com.mommydndn.app.utils.result.Result
import com.mommydndn.app.utils.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState.Loading)
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun signInWithGoogle(authCode: String) {
        viewModelScope.launch {
            signInWithGoogleUseCase(SignInWithGoogleParams(authCode)).let { result ->
                if (result is Result.Failure) {
                    _uiState.emit(SignInUiState.Failure(result.exception))
                } else {
                    result.data?.let { accessToken ->
                        signIn(OAuthProvider.Google, accessToken)
                    }
                }
            }
        }
    }

    fun signIn(oauthProvider: OAuthProvider, accessToken: String) {
        viewModelScope.launch {
            signInUseCase(SignInParams(oauthProvider, accessToken)).let { result ->
                val uiState = if (result is Result.Failure) {
                    // todo: not signed up yet
                    /*
                    SignInUiState.NotSignedUpYet(accessToken, oAuthProvider)
                     */
                    SignInUiState.Failure(result.exception)
                } else {
                    SignInUiState.Success
                }

                _uiState.emit(uiState)
            }
        }
    }
}

sealed interface SignInUiState {

    data object Loading : SignInUiState

    data object Success : SignInUiState

    data class Failure(val exception: Exception) : SignInUiState

    data class NotSignedUpYet(val accessToken: String, val oAuthProvider: OAuthProvider) :
        SignInUiState
}