package com.mommydndn.app.ui.feature.care.joboffer.write

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeItem
import com.mommydndn.app.data.model.user.UserInfo
import com.mommydndn.app.data.model.care.WorkHoursType
import com.mommydndn.app.data.model.care.WorkHoursTypeItem
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.LocationRepository
import com.mommydndn.app.data.respository.UserRepository
import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.NumberUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class JobOfferWriteViewModel @Inject constructor(
    private val caringRepository: CaringRepository,
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private var _careTypes: MutableStateFlow<List<CaringTypeItem>> = MutableStateFlow(
        listOf(
            CaringTypeItem(CaringType.HOUSEKEEPING),
            CaringTypeItem(CaringType.NURSING),
            CaringTypeItem(CaringType.PARENTING),
            CaringTypeItem(CaringType.SCHOOL)
        )
    )
    val careTypes: StateFlow<List<CaringTypeItem>> = _careTypes

    private var _workHoursTypes: MutableStateFlow<List<WorkHoursTypeItem>> = MutableStateFlow(
        listOf(
            WorkHoursTypeItem(WorkHoursType.SHORT, true),
            WorkHoursTypeItem(WorkHoursType.REGULAR)
        )
    )
    val workHoursTypes: StateFlow<List<WorkHoursTypeItem>> = _workHoursTypes


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
            DayOfWeekItem(DayOfWeekType.MONDAY),
            DayOfWeekItem(DayOfWeekType.TUESDAY),
            DayOfWeekItem(DayOfWeekType.WEDNESDAY),
            DayOfWeekItem(DayOfWeekType.THURSDAY),
            DayOfWeekItem(DayOfWeekType.FRIDAY),
            DayOfWeekItem(DayOfWeekType.SATURDAY),
            DayOfWeekItem(DayOfWeekType.SUNDAY)
        )
    )
    val dayOfWeekTypes: StateFlow<List<DayOfWeekItem>> = _daysOfWeekTypes

    private val _title: MutableStateFlow<String> = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _content: MutableStateFlow<String> = MutableStateFlow("")
    val content: StateFlow<String> = _content

    private val _startDate: MutableStateFlow<LocalDate?> = MutableStateFlow(null)
    val stratDate: StateFlow<LocalDate?> = _startDate

    private val _endDate: MutableStateFlow<LocalDate?> = MutableStateFlow(null)
    val endDate: StateFlow<LocalDate?> = _endDate

    private val _startTime: MutableStateFlow<LocalTime?> = MutableStateFlow(null)
    val startTime: StateFlow<LocalTime?> = _startTime

    private val _endTime: MutableStateFlow<LocalTime?> = MutableStateFlow(null)
    val endTime: StateFlow<LocalTime?> = _endTime

    private val _salary: MutableStateFlow<Int?> = MutableStateFlow(null)
    val salary: StateFlow<Int?> = _salary

    private val _photos: MutableStateFlow<List<Uri>> = MutableStateFlow(listOf())
    val photos: StateFlow<List<Uri>> = _photos

    private val _keyword: MutableStateFlow<String> = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword

    private val _userInfo: MutableStateFlow<UserInfo?> = MutableStateFlow(null)
    val userInfo: StateFlow<UserInfo?> = _userInfo

    private val _etcCheckList: MutableStateFlow<List<EtcCheckItem>> = MutableStateFlow(emptyList())
    val etcCheckList: StateFlow<List<EtcCheckItem>> = _etcCheckList

    private val _searchedTownsFlowByKeyword: Flow<PagingData<EmdItem>> = _keyword
        .debounce(200)
        .distinctUntilChanged()
        .flatMapLatest {
            locationRepository.fetchLocationsByKeyword(it)
        }.cachedIn(viewModelScope)
    val searchedTownsFlow: Flow<PagingData<EmdItem>> = _searchedTownsFlowByKeyword

    init {
        fetchUserInfo()
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setContent(content: String) {
        _content.value = content
    }

    fun setStartDate(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val localDate = DateTimeUtils.getLocalDate(year, month, dayOfMonth)
        _startDate.value = localDate
    }

    fun setEndDate(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val localDate = DateTimeUtils.getLocalDate(year, month, dayOfMonth)
        _endDate.value = localDate
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

    fun selectWorkHoursType(selectedTypeItem: WorkHoursTypeItem) {
        _workHoursTypes.value = _workHoursTypes.value.map { item ->
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

    fun setKeyword(keyword: String) {
        _keyword.value = keyword
    }

    fun checkEtcListItem(etcCheckItem: EtcCheckItem) {
        _etcCheckList.value = _etcCheckList.value.map { item ->
            if (item == etcCheckItem) item.copy(isChecked = !item.isChecked)
            else item
        }
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            userRepository.fetchUserInfo().collect { info ->
                _userInfo.value = info
                fetchEtcCheckList(info)
            }
        }
    }

    private fun fetchEtcCheckList(userInfo: UserInfo) {
        viewModelScope.launch {
            caringRepository.fetchEtcCheckList(userInfo.userType).collect {
                _etcCheckList.value = it
            }
        }
    }

}