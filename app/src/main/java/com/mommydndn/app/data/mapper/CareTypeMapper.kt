package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.domain.model.CareType

internal fun NetworkCareType.toCareType(): CareType {
    return when (this) {
        NetworkCareType.PARENTING -> CareType.ChildCare
        NetworkCareType.NURSING -> CareType.SeniorCare
        NetworkCareType.SCHOOL -> CareType.SchoolTransportation
        NetworkCareType.HOUSEKEEPING -> CareType.Housekeeping
    }
}

internal fun List<NetworkCareType>.mapToDomainCareTypes(): List<CareType> = map {
    when (it) {
        NetworkCareType.PARENTING -> CareType.ChildCare
        NetworkCareType.NURSING -> CareType.SeniorCare
        NetworkCareType.SCHOOL -> CareType.SchoolTransportation
        NetworkCareType.HOUSEKEEPING -> CareType.Housekeeping
    }
}
