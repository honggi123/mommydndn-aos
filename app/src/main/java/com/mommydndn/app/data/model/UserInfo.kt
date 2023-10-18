package com.mommydndn.app.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    @SerialName("age")
    val age: Int,
    @SerialName("emd")
    val emd: Emd,
    @SerialName("gender")
    val gender: String,
    @SerialName("hasCompany")
    val hasCompany: Boolean,
    @SerialName("hasJobSeeker")
    val hasJobSeeker: Boolean,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileUrl")
    val profileUrl: String,
    @SerialName("userId")
    val userId: Int,
    @Serializable(with = UserTypeSerializer::class)
    @SerialName("userType")
    val userType: UserType,
    @SerialName("certificationList")
    val certificationList: List<String>
)

@Serializable
data class Emd(
    @SerialName("ctprvnName")
    val ctprvnName: String,
    @SerialName("fullName")
    val fullName: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("sigName")
    val sigName: String
)

