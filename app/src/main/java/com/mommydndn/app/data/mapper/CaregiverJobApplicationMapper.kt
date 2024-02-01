package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkNearbyCareWorker
import com.mommydndn.app.domain.model.CaregiverJobApplication

fun List<NetworkNearbyCareWorker>.transformToDomainCaregiverJobApplication() : List<CaregiverJobApplication> = map {
    TODO()
}