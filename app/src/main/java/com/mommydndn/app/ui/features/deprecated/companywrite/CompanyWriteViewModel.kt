package com.mommydndn.app.ui.features.deprecated.companywrite

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.location.EmdItem
import com.mommydndn.app.domain.model.location.LocationInfo
import com.mommydndn.app.domain.repository.CareRepository
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.util.NumberUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyWriteViewModel @Inject constructor(
    private val caringRepository: CareRepository,
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private var _careTypes: MutableStateFlow<List<CaringTypeItem>> = MutableStateFlow(emptyList())
    val careTypes: StateFlow<List<CaringTypeItem>> = _careTypes

    private val _startSalary: MutableStateFlow<Int?> = MutableStateFlow(null)
    val startSalary: StateFlow<Int?> = _startSalary

    private val _endSalary: MutableStateFlow<Int?> = MutableStateFlow(null)
    val endSalary: StateFlow<Int?> = _endSalary

    private val _introduce: MutableStateFlow<String> = MutableStateFlow("")
    val introduce: StateFlow<String> = _introduce

    private val _photo: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val photo: StateFlow<Uri?> = _photo

    private val _photos: MutableStateFlow<List<Uri>> = MutableStateFlow(listOf())
    val photos: StateFlow<List<Uri>> = _photos

    private val _etcCheckList: MutableStateFlow<List<EtcCheckItem>> = MutableStateFlow(emptyList())
    val etcCheckList: StateFlow<List<EtcCheckItem>> = _etcCheckList

    private val _emdItem: MutableStateFlow<EmdItem?> = MutableStateFlow<EmdItem?>(null)
    val emdItem: StateFlow<EmdItem?> = _emdItem

    private val _commission: MutableStateFlow<Int?> = MutableStateFlow<Int?>(null)
    val commission: StateFlow<Int?> = _commission

    private val _locationInfo: MutableStateFlow<LocationInfo?> =
        MutableStateFlow<LocationInfo?>(null)
    val locationInfo: StateFlow<LocationInfo?> = _locationInfo

    val minHourlySalary: StateFlow<MinHourlySalary?> =
        caringRepository.fetchMinHourlySalary().stateIn(
            viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    init {
        fetchEtcCheckList()
        fetchCaringTypeItems()
        fetchUserInfo()
    }

    fun selectCareTypes(selectedCareType: CaringTypeItem) {
        _careTypes.value = _careTypes.value.map { item ->
            if (item == selectedCareType) item.copy(isSelected = !item.isSelected)
            else item
        }
    }

    fun setIntroduce(curIntroduce: String) {
        _introduce.value = curIntroduce
    }

    fun setStartSalary(curSalary: String) {
        _startSalary.value = NumberUtils.getPrice(curSalary)
    }

    fun setEndSalary(curSalary: String) {
        _endSalary.value = NumberUtils.getPrice(curSalary)
    }

    fun setCommission(curCommission: Int) {
        _commission.value = curCommission
    }

    fun addSelectedPhoto(selectedPhoto: Uri) {
        _photo.value = selectedPhoto
    }

    fun addSelectedPhotos(selectedPhotos: List<Uri>) {
        _photos.value = selectedPhotos
    }

    fun searchLocationByAddress(address: String) {
        viewModelScope.launch {
            locationRepository.fetchAddressByKeyword(address).collectLatest {
                /*
                val address = it.documents.get(0).address
                _locationInfo.value = LocationInfo(
                    latitude = address.y.toDouble(),
                    longitude = address.x.toDouble()
                )

                _emdItem.value = EmdItem(
                    id = Integer.parseInt(address.bCode.subSequence(0, 7).toString()),
                    name = address.region3DepthHName,
                    sigName = address.region2DepthName,
                    ctprvnName = address.region1DepthName,
                    fullName = "${address.region1DepthName} ${address.region2DepthName} ${address.region3DepthHName}"
                )
                 */
            }
        }
    }

    fun removePhoto(selectedUri: Uri) {
        _photos.value = _photos.value.filter {
            it != selectedUri
        }
    }

    fun checkEtcListItem(etcCheckItem: EtcCheckItem) {
        _etcCheckList.value = _etcCheckList.value.map { item ->
            if (item == etcCheckItem) item.copy(isChecked = !item.isChecked)
            else item
        }
    }

    fun fetchCaringTypeItems() {
        viewModelScope.launch {
            caringRepository.fetchCaringTypeItems().collect { types ->
                _careTypes.value = types
            }
        }
    }

    fun fetchUserInfo() {
        viewModelScope.launch {
            /*
            userRepository.getUser().collect { userInfo ->
                _emdItem.value = userInfo.emd
                _photo.value = userInfo.profileUrl?.toUri()
            }
             */
        }
    }

    fun fetchEtcCheckList() {
        viewModelScope.launch {
            caringRepository.fetchCompanyEtcCheckList().collect {
                _etcCheckList.value = it
            }
        }
    }
}