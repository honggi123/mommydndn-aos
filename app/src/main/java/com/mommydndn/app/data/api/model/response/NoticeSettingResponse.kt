package com.mommydndn.app.data.api.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("NoticeSettingResponse")
data class NoticeSettingResponse(
    val isApproved: Boolean,
    val noticeTypeId: Int,
    val noticeTypeName: String
)