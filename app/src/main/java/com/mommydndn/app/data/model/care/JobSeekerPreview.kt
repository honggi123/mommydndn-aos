package com.mommydndn.app.data.model.care

import com.mommydndn.app.domain.model.location.Neighborhood

data class JobSeekerPreview(
    val introduce: String,
    val caringTypeList: List<CaringType>,
    // val emd: EmdItem,
    val salaryType: SalaryType,
    val salary: Int,
    val neighborhood: Neighborhood?,
    val etcCheckedList: List<EtcCheckItem>,
    val imageUri: String,
)
