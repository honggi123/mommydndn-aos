package com.mommydndn.app.ui.features.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.usecase.user.GetAccessTokenParams
import com.mommydndn.app.domain.usecase.user.GetAccessTokenUseCase
import com.mommydndn.app.domain.usecase.user.SignInParams
import com.mommydndn.app.domain.usecase.user.SignInUseCase
import com.mommydndn.app.util.result.Result
import com.mommydndn.app.util.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState.Loading)
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun signInWithGoogle(authCode: String) {
        viewModelScope.launch {
            getAccessTokenUseCase(GetAccessTokenParams(authCode)).let { result ->
                if (result is Result.Failure) {
                    _uiState.emit(SignInUiState.Failure(result.exception))
                } else {
                    result.data?.let { accessToken ->
                        signIn(OAuthProvider.GOOGLE, accessToken)
                    }
                }
            }
        }
    }

    fun signIn(oAuthProvider: OAuthProvider, accessToken: String) {
        viewModelScope.launch {
            signInUseCase(SignInParams(oAuthProvider, accessToken)).let { result ->
                val uiState = if (result is Result.Failure) {
                    // todo: check if just not signed up yet
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

