package com.mommydndn.app.ui.deprecated.write.preview

import android.content.Context
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mommydndn.app.data.model.care.JobOfferPreview
import com.mommydndn.app.data.network.model.response.GetUserResponse
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.utils.extensions.asMultipart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class JobOfferPreviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    // private val caringRepository: CaringRepository,
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {
    private val _authorInfo = MutableStateFlow<GetUserResponse?>(null)
    val authorInfo: StateFlow<GetUserResponse?> = _authorInfo.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

    fun createJobOffer(
        navController: NavHostController,
        context: Context,
        jobOfferPreview: JobOfferPreview
    ) {
        viewModelScope.launch {
            jobOfferPreview.apply {
                /*
                caringRepository.createJobOffer(
                    title = title,
                    content = content,
                    caringTypeList = caringTypeList,
                    taskType = taskType,
                    days = days,
                    dateList = dateList,
                    startDate = startDate,
                    endDate = endDate,
                    startTime = startTime,
                    endTime = endTime,
                    emd = emd,
                    latitude = latitude!!,
                    longitude = longitude!!,
                    salaryType = salaryType,
                    salary = salary,
                    etcCheckedList = etcCheckedList,
                    imageList = convertToImageParts(imageList.map { it.toUri() }, context),
                ).collectLatest {
                    NavigationUtils.navigate(
                        navController,
                        MainNav.Care.route,
                        isLaunchSingleTop = true
                    )
                }
                 */
            }

        }
    }

    private fun convertToImageParts(list: List<Uri>, context: Context): List<MultipartBody.Part> {
        return list.mapIndexedNotNull { index, uri ->
            uri.asMultipart("file_$index", context)
        }
    }
}