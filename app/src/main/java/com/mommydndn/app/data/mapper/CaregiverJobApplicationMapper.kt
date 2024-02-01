package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkNearbyCareWorker
import com.mommydndn.app.domain.model.CaregiverJobApplication
import com.mommydndn.app.domain.model.Gender
import com.mommydndn.app.domain.model.User

fun List<NetworkNearbyCareWorker>.mapToDomainCaregiverJobApplications(): List<CaregiverJobApplication> =
    map {
        CaregiverJobApplication().copy(
            id = it.id.toString(),
            caregiver = User().copy(
                age = parseAgeRange(it.ageAndGender),
                gender = parseGender(it.ageAndGender)
            ),
            careTypes = listOf(it.careType.transformToDomainCareType()),
        )
    }

internal fun parseAgeRange(ageRange: String) = ageRange.drop(1).toInt()

internal fun parseGender(gender: String) = when (gender) {
    "남성" -> Gender.Male
    "여성" -> Gender.Female
    else -> Gender.Unknown
}


