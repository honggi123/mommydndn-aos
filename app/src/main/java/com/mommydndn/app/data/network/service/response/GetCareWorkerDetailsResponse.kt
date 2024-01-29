package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkCareWorker
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import com.mommydndn.app.data.network.model.NetworkPayPeriod
import com.mommydndn.app.data.network.model.NetworkWorkerOtherCondition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareWorkerDetailsResponse(
    @SerialName("jobSeekerId")
    val id: Int,
    @SerialName("jobSeekerAuthor")
    val careWorker: NetworkCareWorker,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("emd")
    val neighborhood: NetworkNeighborhood,
    val latitude: Double,
    val longitude: Double,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    @SerialName("salaryTypeCode")
    val payPeriod: NetworkPayPeriod,
    @SerialName("salary")
    val desiredPay: Int,
    @SerialName("indOtherConditionList")
    val otherConditions: List<NetworkWorkerOtherCondition>,
    val isLiked: Boolean,
)