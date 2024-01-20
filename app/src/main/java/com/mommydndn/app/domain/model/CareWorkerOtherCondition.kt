package com.mommydndn.app.domain.model

sealed interface OtherCondition

enum class CareWorkerOtherCondition : OtherCondition {
    Pets,
    CCTV,
    Residential,
    NonSmoker,
    NoReligion,
}

enum class CareAgencyOtherCondition : OtherCondition {
    AS
}