package com.mommydndn.app.data.model.care

import com.mommydndn.app.data.model.map.EmdItem

data class CompanyPreview(
    val introduce: String,
    val caringTypeList: List<CaringType>,
    val emd: EmdItem,
    val salaryType: SalaryType,
    val salary: Int,
    val etcCheckedList: List<EtcCheckItem>,
    val profileImage: String,
    val coverImageList: List<String>,
)

