package com.example.lokal_assignment.data.repository.datasource

import com.example.lokal_assignment.data.model.CryptoCurrListResponse
import com.example.lokal_assignment.data.model.CryptoLiveExchangeRateResponse
import retrofit2.Response

interface RemoteData {
    suspend fun getAllCryptoCurrencyList() : Response<CryptoCurrListResponse>
    suspend fun getCryptoCurrencyLive() : Response<CryptoLiveExchangeRateResponse>
}