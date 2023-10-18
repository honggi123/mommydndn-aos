package com.mommydndn.app.data.model.care

data class JobOffer(
    val title: String,
    val neighborhood: String,
    val salary: Int,
    val salaryType: SalaryType,
    val caringType: CaringType
)

