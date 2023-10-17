package com.mommydndn.app.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    @SerialName("age")
    val age: Int?,
    @SerialName("certificationList")
    val certificationList: List<String>,
    @SerialName("emd")
    val emd: Emd,
    @SerialName("gender")
    val gender: String?,
    @SerialName("isJobSeeker")
    val isJobSeeker: Boolean,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileUrl")
    val profileUrl: String?,
    @SerialName("userId")
    val userId: Int
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