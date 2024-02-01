package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkNearbyCareJob
import com.mommydndn.app.domain.model.CaregiverJobPosting

fun List<NetworkNearbyCareJob>.toDomain() : List<CaregiverJobPosting>  = map {
    TODO()
}