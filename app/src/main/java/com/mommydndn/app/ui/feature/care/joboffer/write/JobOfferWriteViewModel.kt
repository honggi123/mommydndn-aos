package com.mommydndn.app.ui.feature.care.joboffer.write

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.mommydndn.app.data.model.CaringType
import com.mommydndn.app.data.model.CaringTypeItem
import com.mommydndn.app.data.model.DayOfWeekItem
import com.mommydndn.app.data.model.DayOfWeekType
import com.mommydndn.app.data.model.SalaryType
import com.mommydndn.app.data.model.SalaryTypeItem
import com.mommydndn.app.data.model.WorkHoursType
import com.mommydndn.app.data.model.WorkHoursTypeItem
import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.NumberUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class JobOfferWriteViewModel @Inject constructor(
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
}