package com.mommydndn.app.data.network.service.kakao.response

import com.mommydndn.app.data.network.service.common.model.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetKakaoAddressResponse(
    @SerialName("documents")
    val documents: List<AddressDocumentApiModel>,
    @SerialName("meta")
    val meta: MetaApiModel
)

@Serializable
data class AddressDocumentApiModel(
    @SerialName("address_name")
    val addressName: String,
    @SerialName("address_type")
    val addressType: String,
    val address: AddressApiModel,
    @SerialName("road_address")
    val roadAddress: RoadAddressApiModel?,
    val y: String,
    val x: String,
)

@Serializable
data class AddressApiModel(
    @SerialName("address_name")
    val addressName: String,
    @SerialName("region_1depth_name")
    val region1DepthName: String,
    @SerialName("region_2depth_name")
    val region2DepthName: String,
    @SerialName("region_3depth_name")
    val region3DepthName: String,
    @SerialName("region_3depth_h_name")
    val region3DepthHName: String,
    @SerialName("h_code")
    val hCode: String,
    @SerialName("b_code")
    val bCode: String,
    @SerialName("mountain_yn")
    val mountainYN: String,
    @SerialName("main_address_no")
    val mainAddressNo: String,
    @SerialName("sub_address_no")
    val subAddressNo: String,
    val x: String,
    val y: String
)

@Serializable
data class RoadAddressApiModel(
    @SerialName("address_name")
    val addressName: String,
    @SerialName("region_1depth_name")
    val region1DepthName: String,
    @SerialName("region_2depth_name")
    val region2DepthName: String,
    @SerialName("region_3depth_name")
    val region3DepthName: String,
    @SerialName("road_name")
    val roadName: String,
    @SerialName("underground_yn")
    val undergroundYN: String,
    @SerialName("main_building_no")
    val mainBuildingNo: String,
    @SerialName("sub_building_no")
    val subBuildingNo: String,
    @SerialName("building_name")
    val buildingName: String,
    @SerialName("zone_no")
    val zoneNo: String,
    val x: String,
    val y: String
)