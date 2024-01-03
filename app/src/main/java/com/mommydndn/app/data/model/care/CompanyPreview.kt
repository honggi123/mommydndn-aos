package com.mommydndn.app.data.model.care

import com.mommydndn.app.domain.model.location.Neighborhood

data class CompanyPreview(
    val introduce: String,
    val caringTypeList: List<CaringType>,
    // val emd: EmdItem,
    val startSalary: Int,
    val endSalary: Int,
    val commission: Int,
    val neighborhood: Neighborhood?,
    val etcCheckedList: List<EtcCheckItem>,
    val profileImage: String,
    val coverImageList: List<String>,
)

