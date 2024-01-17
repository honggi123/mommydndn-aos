package com.mommydndn.app.data.network.model

import kotlinx.serialization.Serializable

@Serializable
enum class NetworkSortBy {
    LATEST, MOST_VIEW, HIGHEST_SALARY, CLOSEST
}