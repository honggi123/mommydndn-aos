package com.mommydndn.app.domain.model.TermsAndConditions

data class TermsAndConditionsItem(
    val isRequired: Boolean,
    val name: String,
    val termsId: Int,
    val url: String,
    val isSelected: Boolean = false
)

