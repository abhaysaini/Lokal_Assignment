package com.example.lokal_assignment.data.model

import com.google.gson.annotations.SerializedName

class CryptoCurrListResponse (
    @SerializedName("crypto")
    val crypto: Map<String, CryptoAPIResponse>
)