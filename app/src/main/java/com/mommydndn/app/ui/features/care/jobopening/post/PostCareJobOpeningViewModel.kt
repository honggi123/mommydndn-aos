package com.mommydndn.app.ui.features.care.jobopening.post

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostCareJobOpeningViewModel @Inject constructor(
) : ViewModel(), PostCareJobOpeningDelegate by PostCareJobOpeningViewModelDelegate {

}

