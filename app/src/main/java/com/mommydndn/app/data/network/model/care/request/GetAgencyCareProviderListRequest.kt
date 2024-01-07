package com.mommydndn.app.data.network.model.care.request

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.network.model.care.CareTypeApiModel
import com.mommydndn.app.data.network.model.common.PaginationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgencyCareProviderListRequest(
    @SerialName("sortingCondition")
    val sortingType: SortingType,
    @SerialName("emdId")
    val emdId: Int,
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("minMonthlySalary")
    val minMonthlySalary: Int?,
    @SerialName("maxMonthlySalary")
    val maxMonthlySalary: Int?,
    @SerialName("paginationRequest")
    val pageMeta: PaginationApiModel,
    @SerialName("caringTypeCodeList")
    val careTypeList: List<CareTypeApiModel>,
    val keyword: String?
)

