package com.mommydndn.app.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.model.LoginType
import com.mommydndn.app.data.respository.AccountRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {
    fun signIn(tokenId: String, type: LoginType) {
        viewModelScope.launch {
            accountRepository.logIn(tokenId, type)
        }
    }
}