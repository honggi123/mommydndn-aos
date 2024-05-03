package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkNearbyCareJob
import com.mommydndn.app.domain.model.CaregiverJobPosting
import com.mommydndn.app.domain.model.Neighborhood
import com.mommydndn.app.domain.model.Pay
import com.mommydndn.app.domain.model.User

fun List<NetworkNearbyCareJob>.toCaregiverJobPostings(): List<CaregiverJobPosting> = map {
    CaregiverJobPosting().copy(
        id = it.id.toString(),
        employer = User().copy(
            neighborhood = Neighborhood().copy(name = it.neighborhoodName),
        ),
        title = it.title,
        careTypes = listOf(it.careType.toCareType()),
        pay = Pay(it.payPeriod.toPayPeriod(), it.pay),
    )
}