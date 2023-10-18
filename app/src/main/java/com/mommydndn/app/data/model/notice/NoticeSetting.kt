package com.mommydndn.app.data.model.notice

import com.google.gson.annotations.SerializedName

data class NoticeSetting(
    val isApproved: Boolean,
    val noticeTypeId: Int,
    val noticeTypeName: String,
    val isSelected: Boolean
)
