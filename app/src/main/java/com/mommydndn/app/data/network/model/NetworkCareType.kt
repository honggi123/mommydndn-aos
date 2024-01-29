package com.mommydndn.app.data.network.model

import kotlinx.serialization.Serializable

@Serializable
enum class NetworkCareType {
    PARENTING,
    NURSING,
    SCHOOL,
    HOUSEKEEPING,
}