package com.mommydndn.app.ui.feature.care.joboffer.write

import androidx.lifecycle.ViewModel
import com.mommydndn.app.data.api.model.BabyItem
import com.mommydndn.app.data.model.CaringType
import com.mommydndn.app.data.model.CaringTypeItem
import com.mommydndn.app.data.model.DayOfWeekItem
import com.mommydndn.app.data.model.DayOfWeekType
import com.mommydndn.app.data.model.SalaryType
import com.mommydndn.app.data.model.SalaryTypeItem
import com.mommydndn.app.data.model.WorkHoursType
import com.mommydndn.app.data.model.WorkHoursTypeItem
import com.mommydndn.app.data.respository.BabyItemRepository
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.CommonRepositoy
import com.mommydndn.app.data.respository.NoticeRepository
import com.mommydndn.app.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
            WorkHoursTypeItem(WorkHoursType.SHORT),
            WorkHoursTypeItem(WorkHoursType.REGULAR)
        )
    )
    val workHoursTypes: StateFlow<List<WorkHoursTypeItem>> = _workHoursTypes


    private var _salaryTypes: MutableStateFlow<List<SalaryTypeItem>> = MutableStateFlow(
        listOf(
            SalaryTypeItem(SalaryType.HOURLY),
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

    private val _startDate: MutableStateFlow<Long?> = MutableStateFlow(null)
    val stratDate: StateFlow<Long?> = _startDate

    private val _endDate: MutableStateFlow<Long?> = MutableStateFlow(null)
    val endDate: StateFlow<Long?> = _endDate

    private val _startTimeText: MutableStateFlow<String?> = MutableStateFlow(null)
    val startTimeText: StateFlow<String?> = _startTimeText

    private val _endTimeText: MutableStateFlow<String?> = MutableStateFlow(null)
    val endTimeText: StateFlow<String?> = _endTimeText

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
        val timestamp = DateUtils.dateToTimestamp(year, month, dayOfMonth)
        _startDate.value = timestamp
    }

    fun setEndDate(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val timestamp = DateUtils.dateToTimestamp(year, month, dayOfMonth)
        _endDate.value = timestamp
    }

    fun setStartTime(
        hour: Int,
        min: Int,
    ) {
        _startTimeText.value = "$hour:$min"
    }

    fun setEndTime(
        hour: Int,
        min: Int
    ) {
        _endTimeText.value = "$hour:$min"
    }

    fun setWorkHoursType(selectedTypeItem: WorkHoursTypeItem) {
        _workHoursTypes.value = _workHoursTypes.value.map { item ->
            if (item == selectedTypeItem) item.copy(isSelected = true)
            else item.copy(isSelected = false)
        }
    }

    fun setSalaryType(selectedSalaryTypeItem: SalaryTypeItem) {
        _salaryTypes.value = _salaryTypes.value.map { item ->
            if (item == selectedSalaryTypeItem) item.copy(isSelected = true)
            else item.copy(isSelected = false)
        }
    }

    fun setCareTypes(selectedCareType: CaringTypeItem) {
        _careTypes.value = _careTypes.value.map { item ->
            if (item == selectedCareType) item.copy(isSelected = true)
            else item
        }
    }
}