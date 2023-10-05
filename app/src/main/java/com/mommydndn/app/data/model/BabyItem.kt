package com.mommydndn.app.data.model

import com.google.gson.annotations.SerializedName

data class BabyItem(
    val createdAt: Int,
    val imageUrl: String,
    val itemId: Int,
    val neighborhood: String,
    val price: Int,
    val title: String
)
