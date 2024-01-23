package com.mommydndn.app.domain.model

// TODO: 네이밍
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