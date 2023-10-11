package com.mommydndn.app.data.model

import java.text.NumberFormat
import java.util.Locale

data class JobOffer(
    val title: String,
    val neighborhood: String,
    val salary: Int,
    val salaryType: SalaryType,
    val caringType: CaringType
)

