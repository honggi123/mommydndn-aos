package com.mommydndn.app.ui.care.job

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostCareJobViewModel @Inject constructor(
) : ViewModel(), PostCareJobOpeningDelegate by PostCareJobOpeningViewModelDelegate {

}

