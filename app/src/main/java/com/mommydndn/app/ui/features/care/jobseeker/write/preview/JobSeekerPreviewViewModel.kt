package com.mommydndn.app.ui.features.care.jobseeker.write.preview

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mommydndn.app.data.api.model.response.UserResponse
import com.mommydndn.app.data.model.care.JobOfferPreview
import com.mommydndn.app.data.model.care.JobSeekerPreview
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.LocationRepository
import com.mommydndn.app.data.respository.UserRepository
import com.mommydndn.app.ui.extensions.asMultipart
import com.mommydndn.app.ui.navigation.MainNav
import com.mommydndn.app.utils.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class JobSeekerPreviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val caringRepository: CaringRepository,
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _jobSeekerPreview = MutableStateFlow<JobSeekerPreview?>(null)
    val jobSeekerPreview: StateFlow<JobSeekerPreview?> = _jobSeekerPreview

    val authorInfo: StateFlow<UserResponse?> = userRepository.fetchUserInfo().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

    fun updateJobSeekerPreview(jobSeekerPreview: JobSeekerPreview) {
        viewModelScope.launch {
            _jobSeekerPreview.value = jobSeekerPreview
        }
    }



}