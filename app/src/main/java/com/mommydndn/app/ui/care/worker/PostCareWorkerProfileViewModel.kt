package com.mommydndn.app.ui.care.worker

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostCareWorkerProfileViewModel @Inject constructor() : ViewModel(),
    PostCareWorkerProfileDelegate by PostCareWorkerProfileViewModelDelegate {

}