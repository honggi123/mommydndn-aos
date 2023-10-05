package com.mommydndn.app.data.model

data class JobOffer(
    val title: String,
    val neighborhood: String,
    val salary: String,
    val salaryType: SalaryType,
    val caringType: CaringType
)