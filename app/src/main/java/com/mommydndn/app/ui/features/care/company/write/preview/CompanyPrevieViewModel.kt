package com.mommydndn.app.ui.features.care.company.write.preview

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mommydndn.app.data.api.model.response.UserResponse
import com.mommydndn.app.data.model.care.CompanyPreview
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.UserRepository
import com.mommydndn.app.ui.extensions.asMultipart
import com.mommydndn.app.ui.navigation.MainNav
import com.mommydndn.app.utils.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class CompanyPrevieViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val caringRepository: CaringRepository
) : ViewModel() {

    val authorInfo: StateFlow<UserResponse?> = userRepository.fetchUserInfo().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

    fun createCompany(
        companyPreview: CompanyPreview,
        navController: NavHostController,
        context: Context
    ) {
        viewModelScope.launch {
            companyPreview.apply {
                caringRepository.createCompany(
                    introduce = introduce,
                    coverImageList = convertToImageParts(coverImageList.map { it.toUri() }, context) ,
                    caringTypeList = caringTypeList,
                    emd = emd,
                    latitude = locationInfo?.latitude,
                    longitude = locationInfo?.longitude,
                    minSalary = startSalary,
                    maxSalary = endSalary,
                    etcCheckedList = etcCheckedList,
                    commission = commission
                ).collect {
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

}