package com.mommydndn.app.ui.viewmodel

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    suspend fun getGoogleAccessToken(
        authCode: String,
        clientId: String,
        clientSecret: String
    ): LoginGoogleResponse? = withContext(Dispatchers.IO) {
        val res = accountRepository.getGoogleAccesstoken(authCode, clientId, clientSecret)
        res.body()
    }
}