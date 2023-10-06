package com.mommydndn.app.data.model

import java.text.NumberFormat
import java.util.Locale

data class JobOffer(
    val title: String,
    val neighborhood: String,
    val salary: String,
    val salaryType: SalaryType,
    val caringType: CaringType
)

fun JobOffer.formatSalary(): JobOffer {
    val value = salary.toIntOrNull() ?: 0
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    val formattedSalary = numberFormat.format(value)
    return copy(salary = formattedSalary)
}