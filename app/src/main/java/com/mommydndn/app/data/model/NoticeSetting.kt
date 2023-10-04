package com.mommydndn.app.data.model

import com.google.gson.annotations.SerializedName

data class NoticeSetting(
    val isApproved: Boolean,
    val noticeTypeId: Int,
    val noticeTypeName: String
)
