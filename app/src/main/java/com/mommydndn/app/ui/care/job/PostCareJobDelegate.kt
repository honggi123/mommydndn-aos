package com.mommydndn.app.ui.care.job

import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.OtherOption
import com.mommydndn.app.domain.model.PayPeriod
import com.mommydndn.app.domain.model.WorkPeriod
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class CareJobWorkDateTimesUiModel(
    val workPeriod: WorkPeriod = WorkPeriod.OneTime,
    val dates: List<LocalDate> = emptyList(),
    val daysOfWeek: List<DayOfWeek> = emptyList(),
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
    val negotiable: Boolean = false,
)

// TODO
data class CareJobWorkPlaceUiModel(
    val address: String? = null,
)

interface PostCareJobOpeningDelegate {
    val title: StateFlow<String>
    val content: StateFlow<String>
    val careTypes: StateFlow<List<CareType>>
    val workDateTimes: StateFlow<CareJobWorkDateTimesUiModel>
    val workPlace: StateFlow<CareJobWorkPlaceUiModel>
    val payPeriod: StateFlow<PayPeriod>
    val pay: StateFlow<Int>
    val photoUris: StateFlow<List<String>>
    val options: StateFlow<List<OtherOption>>

    fun setTitle(title: String)
    fun setContent(content: String)
    fun setCareTypes(careTypes: List<CareType>)
    fun setWorkDateTimes(dateTimes: CareJobWorkDateTimesUiModel)
    fun setWorkPlace(workPlace: CareJobWorkPlaceUiModel)
    fun setPayPeriod(payPeriod: PayPeriod)
    fun setPay(pay: Int)
    fun setPhotoUris(photoUris: List<String>)
    fun setOptions(options: List<OtherOption>)
}

object PostCareJobOpeningViewModelDelegate : PostCareJobOpeningDelegate {

    private val _title: MutableStateFlow<String> = MutableStateFlow("")
    override val title: StateFlow<String> = _title.asStateFlow()

    private val _content: MutableStateFlow<String> = MutableStateFlow("")
    override val content: StateFlow<String> = _content.asStateFlow()

    private val _careTypes: MutableStateFlow<List<CareType>> = MutableStateFlow(emptyList())
    override val careTypes: StateFlow<List<CareType>> = _careTypes.asStateFlow()

    private val _workHours: MutableStateFlow<CareJobWorkDateTimesUiModel> = MutableStateFlow(
        CareJobWorkDateTimesUiModel()
    )
    override val workDateTimes: StateFlow<CareJobWorkDateTimesUiModel> = _workHours.asStateFlow()

    private val _workPlace: MutableStateFlow<CareJobWorkPlaceUiModel> = MutableStateFlow(
        CareJobWorkPlaceUiModel()
    )
    override val workPlace: StateFlow<CareJobWorkPlaceUiModel> = _workPlace.asStateFlow()

    private val _payPeriod: MutableStateFlow<PayPeriod> = MutableStateFlow(PayPeriod.Hourly)
    override val payPeriod: StateFlow<PayPeriod> = _payPeriod.asStateFlow()

    private val _pay: MutableStateFlow<Int> = MutableStateFlow(0)
    override val pay: StateFlow<Int> = _pay.asStateFlow()

    private val _photoUris: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    override val photoUris: StateFlow<List<String>> = _photoUris.asStateFlow()

    private val _otherConditions: MutableStateFlow<List<OtherOption>> = MutableStateFlow(OtherOption.values().toList())
    override val options: StateFlow<List<OtherOption>> = _otherConditions.asStateFlow()

    override fun setTitle(title: String) {
        _title.value = title
    }

    override fun setContent(content: String) {
        _content.value = content
    }

    override fun setCareTypes(careTypes: List<CareType>) {
        _careTypes.value = careTypes
    }

    override fun setWorkDateTimes(dateTimes: CareJobWorkDateTimesUiModel) {
        _workHours.value = dateTimes
    }

    override fun setWorkPlace(workPlace: CareJobWorkPlaceUiModel) {
        _workPlace.value = workPlace
    }

    override fun setPayPeriod(payPeriod: PayPeriod) {
        _payPeriod.value = payPeriod
    }

    override fun setPay(pay: Int) {
        _pay.value = pay
    }

    override fun setPhotoUris(photoUris: List<String>) {
        _photoUris.value = photoUris
    }

    override fun setOptions(options: List<OtherOption>) {
        _otherConditions.value = options
    }
}