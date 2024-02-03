package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkNearbyCareWorker
import com.mommydndn.app.domain.model.CaregiverJobApplication
import com.mommydndn.app.domain.model.Gender
import com.mommydndn.app.domain.model.User

fun List<NetworkNearbyCareWorker>.mapToDomainCaregiverJobApplications(): List<CaregiverJobApplication> =
    map {
        val (ageRange, gender) = with(it.ageAndGender.split(" ")) {
            parseAgeRange(get(0)) to parseGender(get(1))
        }

        CaregiverJobApplication().copy(
            id = it.id.toString(),
            caregiver = User().copy(
                age = ageRange,
                gender = gender
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


