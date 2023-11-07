package com.mommydndn.app.ui.features.care.joboffer.write.preview

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mommydndn.app.data.api.model.response.JobOfferResponse
import com.mommydndn.app.data.api.model.response.UserResponse
import com.mommydndn.app.data.model.care.JobOfferPreview
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.LocationRepository
import com.mommydndn.app.data.respository.UserRepository
import com.mommydndn.app.ui.extensions.asMultipart
import com.mommydndn.app.ui.navigation.JobOfferWritePreviewNav
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
class JobOfferPreviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val caringRepository: CaringRepository,
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _jobOfferPreview = MutableStateFlow<JobOfferPreview?>(null)
    val jobOfferPreview: StateFlow<JobOfferPreview?> = _jobOfferPreview

    private val _loactionInfo = MutableStateFlow<LocationInfo?>(null)
    val loactionInfo: StateFlow<LocationInfo?> = _loactionInfo

    val authorInfo: StateFlow<UserResponse?> = userRepository.fetchUserInfo().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

    fun updateJobOfferPreview(jobofferPriview: JobOfferPreview) {
        viewModelScope.launch {
            _jobOfferPreview.value = jobofferPriview
        }
    }

    private fun updateAddress(latitude: Double, longitude: Double) {
        viewModelScope.launch {

            if (latitude != null && longitude != null) {
                val locationInfo = LocationInfo(latitude = latitude, longitude = longitude)

                locationRepository.fetchEmdByLocation(locationInfo).collectLatest { emd ->
                    _loactionInfo.value = locationInfo.copy(address = emd?.fullName ?: "")
                }
            }
        }
    }

    fun createJobOffer(
        navController: NavHostController,
        context: Context,
        jobOfferPreview: JobOfferPreview
    ) {
        viewModelScope.launch {
            jobOfferPreview.apply {
                caringRepository.createJobOffer(
                    title = title,
                    content = content,
                    caringTypeList = caringTypeList,
                    taskType = taskType,
                    startDate = startDate,
                    endDate = endDate,
                    days = days,
                    startTime = startTime,
                    endTime = endTime,
                    emd = emd,
                    latitude = latitude!!,
                    longitude = longitude!!,
                    salaryType = salaryType,
                    salary = salary,
                    etcCheckedList = etcCheckedList,
                    imageList = convertToImageParts(imageList, context),
                ).collectLatest {
                    NavigationUtils.navigate(
                        navController,
                        MainNav.Care.route,
                        isLaunchSingleTop = true
                    )
                }
            }

        }
    }

    private fun convertToImageParts(list: List<Uri>, context: Context): List<MultipartBody.Part> {
        return list.mapIndexedNotNull { index, uri ->
            uri.asMultipart("file_$index", context)
        }
    }

    companion object {
        private const val JOB_OFFER_POST_ID = "jobOfferId"
    }
}