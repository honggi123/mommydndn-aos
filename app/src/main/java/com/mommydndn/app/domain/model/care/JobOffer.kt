package com.mommydndn.app.domain.model.care

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.SalaryType

data class JobOffer(
    val title: String,
    val neighborhood: String,
    val salary: Int,
    val salaryType: SalaryType,
    val caringType: CaringType
)

