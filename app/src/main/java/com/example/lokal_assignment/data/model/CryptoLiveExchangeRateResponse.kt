package com.example.lokal_assignment.data.model

import com.google.gson.annotations.SerializedName

class CryptoLiveExchangeRateResponse (
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("rates")
    val rates: Map<String, Double>
)