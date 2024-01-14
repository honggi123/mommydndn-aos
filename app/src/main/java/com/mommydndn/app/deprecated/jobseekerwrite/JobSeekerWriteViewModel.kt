package com.mommydndn.app.deprecated.jobseekerwrite

import android.content.Context
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeItem
import com.mommydndn.app.data.network.service.user.response.Certification
import com.mommydndn.app.domain.model.location.Neighborhood
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.deprecated.NumberUtils
import com.mommydndn.app.utils.extensions.asMultipart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class JobSeekerWriteViewModel @Inject constructor(
    // private val caringRepository: CaringRepository,
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private var _careTypes: MutableStateFlow<List<CaringTypeItem>> = MutableStateFlow(emptyList())
//    val careTypes: StateFlow<List<CaringTypeItem>> = _careTypes
//
//    private var _certificationList: MutableStateFlow<List<Certification>> =
//        MutableStateFlow(emptyList())
//    val certificationList: StateFlow<List<Certification>> = _certificationList
//
//    private var _salaryTypes: MutableStateFlow<List<SalaryTypeItem>> = MutableStateFlow(
//        listOf(
//            SalaryTypeItem(SalaryType.HOURLY, true),
//            SalaryTypeItem(SalaryType.DAILY),
//            SalaryTypeItem(SalaryType.WEEKLY),
//            SalaryTypeItem(SalaryType.MONTHLY),
//            SalaryTypeItem(SalaryType.NEGOTIATION)
//        )
//    )
//    val salaryTypes: StateFlow<List<SalaryTypeItem>> = _salaryTypes
//
//    private val _introduce: MutableStateFlow<String> = MutableStateFlow("")
//    val introduce: StateFlow<String> = _introduce
//
//    private val _salary: MutableStateFlow<Int?> = MutableStateFlow(null)
//    val salary: StateFlow<Int?> = _salary
//
//    private val _photo: MutableStateFlow<Uri?> = MutableStateFlow(null)
//    val photo: StateFlow<Uri?> = _photo
//
//    private val _etcCheckList: MutableStateFlow<List<EtcCheckItem>> = MutableStateFlow(emptyList())
//    val etcCheckList: StateFlow<List<EtcCheckItem>> = _etcCheckList
//
//    private val _emdItem: MutableStateFlow<EmdItem?> = MutableStateFlow<EmdItem?>(null)
//    val emdItem: StateFlow<EmdItem?> = _emdItem
//
//
//    private val _neighborhood: MutableStateFlow<Neighborhood?> =
//        MutableStateFlow<Neighborhood?>(null)
//    val neighborhood: StateFlow<Neighborhood?> = _neighborhood
//
//    /*
//    val minHourlySalary: StateFlow<MinHourlySalary?> =
//        caringRepository.fetchMinHourlySalary().stateIn(
//            viewModelScope,
//            started = SharingStarted.Lazily,
//            initialValue = null
//        )
//     */
//
//
//    init {
//        fetchEtcCheckList()
//        fetchCaringTypeItems()
//        fetchUserInfo()
//    }
//
//    fun selectSalaryType(selectedSalaryTypeItem: SalaryTypeItem) {
//        _salaryTypes.value = _salaryTypes.value.map { item ->
//            if (item == selectedSalaryTypeItem) item.copy(isSelected = true)
//            else item.copy(isSelected = false)
//        }
//    }
//
//    fun selectCareTypes(selectedCareType: CaringTypeItem) {
//        _careTypes.value = _careTypes.value.map { item ->
//            if (item == selectedCareType) item.copy(isSelected = !item.isSelected)
//            else item
//        }
//    }
//
//    fun setIntroduce(curIntroduce: String) {
//        _introduce.value = curIntroduce
//    }
//
//    fun setSalary(curSalary: String) {
//        _salary.value = NumberUtils.getPrice(curSalary)
//    }
//
//    fun addSelectedPhotos(
//        selectedPhoto: Uri,
//        context: Context
//    ) {
//        viewModelScope.launch {
//            val photoPart = convertToImagePart(selectedPhoto, context)
//            photoPart?.let {
//                /*
//                userRepository.updateProfileImage(photoPart).collect {
//                    _photo.value = selectedPhoto
//                }
//                 */
//            }
//        }
//    }
//
//    fun searchLocationByAddress(address: String) {
//        /*
//        viewModelScope.launch {
//            locationRepository.fetchAddressByKeyword(address).collectLatest {
//                val address = it.documents.get(0).address
//
//                /*
//                _locationInfo.value = LocationInfo(
//                    latitude = address.y.toDouble(),
//                    longitude = address.x.toDouble()
//                )
//                 */
//
//                _emdItem.value = EmdItem(
//                    id = Integer.parseInt(address.bCode.subSequence(0, 7).toString()),
//                    name = address.region3DepthHName,
//                    sigName = address.region2DepthName,
//                    ctprvnName = address.region1DepthName,
//                    fullName = "${address.region1DepthName} ${address.region2DepthName} ${address.region3DepthHName}"
//                )
//            }
//        }
//         */
//    }
//
//    fun checkEtcListItem(etcCheckItem: EtcCheckItem) {
//        _etcCheckList.value = _etcCheckList.value.map { item ->
//            if (item == etcCheckItem) item.copy(isChecked = !item.isChecked)
//            else item
//        }
//    }
//
//    fun fetchCaringTypeItems() {
//        viewModelScope.launch {
//            /*
//            caringRepository.fetchCaringTypeItems().collect { types ->
//                _careTypes.value = types
//            }
//             */
//        }
//    }
//
//    fun fetchUserInfo() {
//        viewModelScope.launch {
//            /*
//            userRepository.getUser().collect { userInfo ->
//                _emdItem.value = userInfo.emd
//                _photo.value = userInfo.profileUrl?.toUri()
//                _certificationList.value = userInfo.certificationList
//            }
//             */
//        }
//    }
//
//
//    fun fetchEtcCheckList() {
//        viewModelScope.launch {
//            /*
//            caringRepository.fetchEtcIndividualCheckList().collect {
//                _etcCheckList.value = it
//            }
//             */
//
//        }
//    }
//
//    private fun convertToImagePart(uri: Uri, context: Context): MultipartBody.Part? {
//        return uri.asMultipart("file_0", context)
//    }
}