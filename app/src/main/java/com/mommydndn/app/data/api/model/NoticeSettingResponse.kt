package com.mommydndn.app.data.api.model


import com.google.gson.annotations.SerializedName

data class NoticeSettingResponse(
    @SerializedName("isApproved")
    val isApproved: Boolean,
    @SerializedName("noticeTypeId")
    val noticeTypeId: Int,
    @SerializedName("noticeTypeName")
    val noticeTypeName: String
)