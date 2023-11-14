package com.mommydndn.app.data.model.care

import android.net.Uri
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.map.EmdItem
import okhttp3.MultipartBody
import java.time.LocalDate
import java.time.LocalTime

data class JobOfferPreview(
    val title: String,
    val content: String,
    val caringTypeList: List<CaringType>,
    val taskType: WorkPeriodType,
    val dateList: List<LocalDate>?,
    val days: List<DayOfWeekItem>,
    val startTime: LocalTime?,
    val endTime: LocalTime?,
    val emd: EmdItem,
    val latitude: Double,
    val longitude: Double,
    val salaryType: SalaryType,
    val salary: Int,
    val etcCheckedList: List<EtcCheckItem>,
    val imageList: List<String>,
    val startDate: LocalDate,
    val endDate: LocalDate
)
