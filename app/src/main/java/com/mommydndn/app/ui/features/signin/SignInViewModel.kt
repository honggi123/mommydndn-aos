package com.mommydndn.app.ui.features.signin

import android.net.Uri
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.mommydndn.app.domain.model.user.OAuthType
import com.mommydndn.app.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import androidx.navigation.NavHostController
import com.mommydndn.app.data.api.model.response.LoginResponse
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.ui.navigation.MainNav
import com.mommydndn.app.ui.navigation.UserTypeNav
import com.mommydndn.app.util.NavigationUtils
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _token = MutableStateFlow("")
    private val _oAuthType = MutableStateFlow<OAuthType?>(null)

    private val _errMsg = MutableStateFlow<String>("")
    val errMsg: StateFlow<String> = _errMsg

    fun signIn(
        tokenId: String,
        type: OAuthType,
        navHostController: NavHostController
    ) {
        viewModelScope.launch {
            _token.value = tokenId
            _oAuthType.value = type

            val response = accountRepository.signIn(tokenId, type)
            handleSignInResponse(response, navHostController)
        }
    }

    fun handleGoogleSignInResult(
        accountTask: Task<GoogleSignInAccount>,

        navHostController: NavHostController
    ) {
        viewModelScope.launch {
            val account = accountTask.result

            account.serverAuthCode?.let {
                val accessToken = getGoogleAccessToken(it)

                if (accessToken != null) {
                    signIn(
                        tokenId = accessToken,
                        type = OAuthType.GOOGLE,
                        navHostController = navHostController
                    )
                }
            }
        }
    }

    private suspend fun getGoogleAccessToken(
        authCode: String,
    ): String? = withContext(Dispatchers.IO) {
        val data = accountRepository
            .getGoogleAccessToken(authCode)
            .getOrNull()
        data?.access_token
    }

    private fun handleSignInResponse(response: ApiResponse<LoginResponse>,  navHostController: NavHostController) {
        response
            .onSuccess {
                NavigationUtils.navigate(navHostController, MainNav.Home.route)
            }
            .onError {
                val message = when (statusCode) {
                    StatusCode.InternalServerError -> "InternalServerError"
                    StatusCode.Forbidden -> {
                        NavigationUtils.navigate(
                            navHostController, UserTypeNav.navigateWithArg(
                                SignUpInfo(
                                    accessToken = Uri.encode(_token.value),
                                    oAuthType = _oAuthType.value
                                )
                            )
                        )
                        "Forbidden"
                    }

                    else -> "$statusCode(${statusCode.code}): ${message()}"
                }
                _errMsg.value = message
            }
            .onException {
                message?.let { _errMsg.value = it }
            }
    }
}