package com.mommydndn.app.data.api.model


import com.google.gson.annotations.SerializedName

data class NearestBabyItemResponse(
    @SerializedName("createdAt")
    val createdAt: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("itemId")
    val itemId: Int,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String
)