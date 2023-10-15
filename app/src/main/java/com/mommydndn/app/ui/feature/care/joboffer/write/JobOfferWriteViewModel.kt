package com.mommydndn.app.ui.feature.care.joboffer.write

import androidx.lifecycle.ViewModel
import com.mommydndn.app.data.api.model.BabyItem
import com.mommydndn.app.data.model.CaringType
import com.mommydndn.app.data.model.CaringTypeItem
import com.mommydndn.app.data.model.SalaryType
import com.mommydndn.app.data.model.SalaryTypeItem
import com.mommydndn.app.data.model.WorkHoursType
import com.mommydndn.app.data.model.WorkHoursTypeItem
import com.mommydndn.app.data.respository.BabyItemRepository
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.CommonRepositoy
import com.mommydndn.app.data.respository.NoticeRepository
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

    fun setWorkHoursType(selectedTypeItem: WorkHoursTypeItem) {
        _workHoursTypes.value = _workHoursTypes.value.map { item ->
            if (item == selectedTypeItem) item.copy(isSelected = true)
            else item
        }
    }

    fun setSalaryType(selectedSalaryTypeItem: SalaryTypeItem) {
        _salaryTypes.value = _salaryTypes.value.map { item ->
            if (item == selectedSalaryTypeItem) item.copy(isSelected = true)
            else item
        }
    }

    fun setCareTypes(selectedCareType: CaringTypeItem) {
        _careTypes.value = _careTypes.value.map { item ->
            if (item == selectedCareType) item.copy(isSelected = true)
            else item
        }
    }
}