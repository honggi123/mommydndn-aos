package com.mommydndn.app.data.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressResponse(
    @SerialName("meta") val meta: MetaData,
    @SerialName("documents") val documents: List<AddressDocument>
)

@Serializable
data class MetaData(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("pageable_count") val pageableCount: Int,
    @SerialName("is_end") val isEnd: Boolean
)

@Serializable
data class AddressDocument(
    @SerialName("address_name") val addressName: String,
    @SerialName("y") val y: String,
    @SerialName("x") val x: String,
    @SerialName("address_type") val addressType: String,
    @SerialName("address") val address: Address,
    @SerialName("road_address") val roadAddress: RoadAddress?
)

@Serializable
data class Address(
    @SerialName("address_name") val addressName: String,
    @SerialName("region_1depth_name") val region1DepthName: String,
    @SerialName("region_2depth_name") val region2DepthName: String,
    @SerialName("region_3depth_name") val region3DepthName: String,
    @SerialName("region_3depth_h_name") val region3DepthHName: String,
    @SerialName("h_code") val hCode: String,
    @SerialName("b_code") val bCode: String,
    @SerialName("mountain_yn") val mountainYN: String,
    @SerialName("main_address_no") val mainAddressNo: String,
    @SerialName("sub_address_no") val subAddressNo: String,
    @SerialName("x") val x: String,
    @SerialName("y") val y: String
)

@Serializable
data class RoadAddress(
    @SerialName("address_name") val addressName: String,
    @SerialName("region_1depth_name") val region1DepthName: String,
    @SerialName("region_2depth_name") val region2DepthName: String,
    @SerialName("region_3depth_name") val region3DepthName: String,
    @SerialName("road_name") val roadName: String,
    @SerialName("underground_yn") val undergroundYN: String,
    @SerialName("main_building_no") val mainBuildingNo: String,
    @SerialName("sub_building_no") val subBuildingNo: String,
    @SerialName("building_name") val buildingName: String,
    @SerialName("zone_no") val zoneNo: String,
    @SerialName("x") val x: String,
    @SerialName("y") val y: String
)