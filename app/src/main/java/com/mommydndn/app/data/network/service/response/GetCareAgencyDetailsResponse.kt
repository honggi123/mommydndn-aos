package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkAgencyOtherCondition
import com.mommydndn.app.data.network.model.NetworkCareAgencyManager
import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkImage
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareAgencyDetailsResponse(
    @SerialName("companyId")
    val id: Long,
    @SerialName("coverImageList")
    val coverImages: List<NetworkImage>,
    @SerialName("companyAuthor")
    val manager: NetworkCareAgencyManager,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("emd")
    val neighborhood: NetworkNeighborhood,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    @SerialName("minMonthlySalary")
    val minSalary: Int,
    @SerialName("maxMonthlySalary")
    val maxSalary: Int,
    val commission: Int,
    @SerialName("comOtherConditionList")
    val otherConditions: List<NetworkAgencyOtherCondition>,
    val isLiked: Boolean,
)