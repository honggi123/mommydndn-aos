package com.mommydndn.app.feature.care.jobopening

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostCareJobOpeningViewModel @Inject constructor(
) : ViewModel(), PostCareJobOpeningDelegate by PostCareJobOpeningViewModelDelegate {

}

