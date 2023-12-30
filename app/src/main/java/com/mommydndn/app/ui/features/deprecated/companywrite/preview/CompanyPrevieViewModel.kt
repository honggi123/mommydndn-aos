package com.mommydndn.app.ui.features.deprecated.companywrite.preview

import androidx.lifecycle.ViewModel
import com.mommydndn.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompanyPrevieViewModel @Inject constructor(
    private val userRepository: UserRepository,
    // private val caringRepository: CaringRepository
) : ViewModel() {

    /*
val authorInfo: StateFlow<GetUserResponse?> = userRepository.getUser().stateIn(
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
    */
}