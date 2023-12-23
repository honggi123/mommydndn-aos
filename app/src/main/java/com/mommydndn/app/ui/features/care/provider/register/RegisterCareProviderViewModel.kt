package com.mommydndn.app.ui.features.care.provider.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterCareProviderViewModel @Inject constructor() : ViewModel(),
    RegisterCareProviderDelegate by RegisterCareProviderViewModelDelegate {

}