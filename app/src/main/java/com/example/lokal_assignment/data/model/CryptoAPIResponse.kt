package com.example.lokal_assignment.data.model

import com.google.gson.annotations.SerializedName

data class CryptoAPIResponse(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("name_full") val fullName: String,
    @SerializedName("icon_url") val iconUrl: String
)
