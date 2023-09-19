package com.mommydndn.app.ui.viewmodel

import android.content.Context
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.mommydndn.app.R
import com.mommydndn.app.data.model.LoginGoogleResponse
import com.mommydndn.app.data.model.LoginType
import com.mommydndn.app.data.respository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    fun signIn(tokenId: String, type: LoginType) {
        viewModelScope.launch {
            accountRepository.signIn(tokenId, type)
        }
    }

    fun handleGoogleSignInResult(
        accountTask: Task<GoogleSignInAccount>,
        clientId: String,
        clientSecret: String
    ) {
        viewModelScope.launch {
            val account = accountTask.result

            account.serverAuthCode?.let {
                val accessToken = getGoogleAccessToken(
                    it,
                    clientId,
                    clientSecret
                )

                if (accessToken != null) {
                    signIn(
                        tokenId = accessToken,
                        type = LoginType.GOOGLE
                    )
                }
            }
        }
    }

    private suspend fun getGoogleAccessToken(
        authCode: String,
        clientId: String,
        clientSecret: String
    ): String? = withContext(Dispatchers.IO) {
        val res = accountRepository.getGoogleAccesstoken(authCode, clientId, clientSecret)
        res.body()?.access_token
    }
}