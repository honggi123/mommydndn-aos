package com.mommydndn.app.ui.features.care.joboffer.write

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mommydndn.app.data.api.model.response.UserResponse
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeItem
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.care.WorkPeriodTypeItem
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.LocationRepository
import com.mommydndn.app.data.respository.UserRepository
import com.mommydndn.app.ui.extensions.asMultipart
import com.mommydndn.app.ui.navigation.JobOfferWritePreviewNav
import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.NavigationUtils
import com.mommydndn.app.utils.NumberUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class JobOfferWriteViewModel @Inject constructor(
    private val caringRepository: CaringRepository,
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _careTypes: MutableStateFlow<List<CaringTypeItem>> = MutableStateFlow(emptyList())
    val careTypes: StateFlow<List<CaringTypeItem>> = _careTypes

    private var _workPeriodTypes: MutableStateFlow<List<WorkPeriodTypeItem>> = MutableStateFlow(
        listOf(
            WorkPeriodTypeItem(WorkPeriodType.ONETIME, true),
            WorkPeriodTypeItem(WorkPeriodType.REGULAR)
        )
    )
    val workPeriodTypes: StateFlow<List<WorkPeriodTypeItem>> = _workPeriodTypes


    private var _salaryTypes: MutableStateFlow<List<SalaryTypeItem>> = MutableStateFlow(
        listOf(
            SalaryTypeItem(SalaryType.HOURLY, true),
            SalaryTypeItem(SalaryType.DAILY),
            SalaryTypeItem(SalaryType.WEEKLY),
            SalaryTypeItem(SalaryType.MONTHLY),
            SalaryTypeItem(SalaryType.NEGOTIATION)
        )
    )
    val salaryTypes: StateFlow<List<SalaryTypeItem>> = _salaryTypes

    private var _daysOfWeekTypes: MutableStateFlow<List<DayOfWeekItem>> = MutableStateFlow(
        listOf(
            DayOfWeekItem(DayOfWeekType.MON),
            DayOfWeekItem(DayOfWeekType.TUE),
            DayOfWeekItem(DayOfWeekType.WED),
            DayOfWeekItem(DayOfWeekType.THU),
            DayOfWeekItem(DayOfWeekType.FRI),
            DayOfWeekItem(DayOfWeekType.SAT),
            DayOfWeekItem(DayOfWeekType.SUN)
        )
    )
    val dayOfWeekTypes: StateFlow<List<DayOfWeekItem>> = _daysOfWeekTypes

    private val _title: MutableStateFlow<String> = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _content: MutableStateFlow<String> = MutableStateFlow("")
    val content: StateFlow<String> = _content

    private val _dateList: MutableStateFlow<List<LocalDate>> = MutableStateFlow(listOf())
    val dateList: StateFlow<List<LocalDate>> = _dateList

    private val _startTime: MutableStateFlow<LocalTime> = MutableStateFlow(LocalTime.now())
    val startTime: StateFlow<LocalTime> = _startTime

    private val _endTime: MutableStateFlow<LocalTime> = MutableStateFlow(LocalTime.now())
    val endTime: StateFlow<LocalTime> = _endTime

    private val _salary: MutableStateFlow<Int?> = MutableStateFlow(null)
    val salary: StateFlow<Int?> = _salary

    private val _photos: MutableStateFlow<List<Uri>> = MutableStateFlow(listOf())
    val photos: StateFlow<List<Uri>> = _photos

    private val _keyword: MutableStateFlow<String> = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword

    private val _etcCheckList: MutableStateFlow<List<EtcCheckItem>> = MutableStateFlow(emptyList())
    val etcCheckList: StateFlow<List<EtcCheckItem>> = _etcCheckList

    private val _isTimeNegotiable: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isTimeNegotiable: StateFlow<Boolean> = _isTimeNegotiable

    private val _emdItem: MutableStateFlow<EmdItem?> = MutableStateFlow<EmdItem?>(null)
    val emdItem: StateFlow<EmdItem?> = _emdItem

    private val _locationInfo: MutableStateFlow<LocationInfo?> =
        MutableStateFlow<LocationInfo?>(null)
    val locationInfo: StateFlow<LocationInfo?> = _locationInfo

    private val _startDate: MutableStateFlow<LocalDate> = MutableStateFlow(LocalDate.now())
    val startDate: StateFlow<LocalDate> = _startDate

    private val _endDate: MutableStateFlow<LocalDate> = MutableStateFlow(LocalDate.now())
    val endDate: StateFlow<LocalDate> = _endDate

    val minHourlySalary: StateFlow<MinHourlySalary?> =
        caringRepository.fetchMinHourlySalary().stateIn(
            viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    init {
        fetchEtcCheckList()
        fetchCaringTypeItems()
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setContent(content: String) {
        _content.value = content
    }

    fun addDate(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val localDate = DateTimeUtils.getLocalDate(year, month, dayOfMonth)

        _dateList.value = _dateList.value.toMutableList().apply { add(localDate) }
    }

    fun removeDate(index: Int) {
        _dateList.value = _dateList.value.toMutableList().apply { removeAt(index) }
    }

    fun setStartTime(
        hour: Int,
        min: Int,
    ) {
        _startTime.value = DateTimeUtils.getLocalTime(hour, min)
    }

    fun setEndTime(
        hour: Int,
        min: Int
    ) {
        _endTime.value = DateTimeUtils.getLocalTime(hour, min)
    }

    fun selectWorkPeriodType(selectedTypeItem: WorkPeriodTypeItem) {
        _workPeriodTypes.value = _workPeriodTypes.value.map { item ->
            if (item == selectedTypeItem) item.copy(isSelected = true)
            else item.copy(isSelected = false)
        }
    }

    fun selectSalaryType(selectedSalaryTypeItem: SalaryTypeItem) {
        _salaryTypes.value = _salaryTypes.value.map { item ->
            if (item == selectedSalaryTypeItem) item.copy(isSelected = true)
            else item.copy(isSelected = false)
        }
    }

    fun selectCareTypes(selectedCareType: CaringTypeItem) {
        _careTypes.value = _careTypes.value.map { item ->
            if (item == selectedCareType) item.copy(isSelected = !item.isSelected)
            else item
        }
    }

    fun setStartDate(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val localDate = DateTimeUtils.getLocalDate(year, month, dayOfMonth)
        _endDate.value = localDate
    }

    fun setEndDate(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val localDate = DateTimeUtils.getLocalDate(year, month, dayOfMonth)
        _endDate.value = localDate
    }

    fun setSalary(curSalary: String) {
        _salary.value = NumberUtils.getPrice(curSalary)
    }

    fun addSelectedPhotos(selectedPhotos: List<Uri>) {
        _photos.value = _photos.value + selectedPhotos
    }

    fun selectDayOfWeek(selectedDayOfWeekItem: DayOfWeekItem) {
        _daysOfWeekTypes.value = _daysOfWeekTypes.value.map { item ->
            if (item == selectedDayOfWeekItem) item.copy(isSelected = !item.isSelected)
            else item
        }
    }

    fun searchLocationByAddress(address: String) {
        viewModelScope.launch {
            locationRepository.fetchAddressByKeyword(address).collectLatest {
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
            }
        }
    }

    fun checkEtcListItem(etcCheckItem: EtcCheckItem) {
        _etcCheckList.value = _etcCheckList.value.map { item ->
            if (item == etcCheckItem) item.copy(isChecked = !item.isChecked)
            else item
        }
    }

    fun toggleIsTimeNegotiable() {
        _isTimeNegotiable.value = !_isTimeNegotiable.value
    }

    fun removePhoto(selectedUri: Uri) {
        _photos.value = _photos.value.filter {
            it != selectedUri
        }
    }

    fun fetchCaringTypeItems() {
        viewModelScope.launch {
            caringRepository.fetchCaringTypeItems().collect { types ->
                _careTypes.value = types
            }
        }
    }

    fun fetchEtcCheckList() {
        viewModelScope.launch {
            caringRepository.fetchEtcIndividualCheckList().collect {
                _etcCheckList.value = it
            }
        }
    }

}