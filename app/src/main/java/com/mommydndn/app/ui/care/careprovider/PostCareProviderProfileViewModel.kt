package com.mommydndn.app.ui.care.careprovider

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostCareProviderProfileViewModel @Inject constructor() : ViewModel(),
    PostCareProviderProfileDelegate by PostCareProviderProfileViewModelDelegate {

}