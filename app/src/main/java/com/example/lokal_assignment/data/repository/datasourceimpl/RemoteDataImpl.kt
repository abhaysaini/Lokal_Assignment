package com.example.lokal_assignment.data.repository.datasourceimpl

import com.example.lokal_assignment.data.model.CryptoCurrListResponse
import com.example.lokal_assignment.data.model.CryptoLiveExchangeRateResponse
import com.example.lokal_assignment.data.repository.datasource.RemoteData
import com.example.lokal_assignment.network.APIservice
import retrofit2.Response

class RemoteDataImpl(val apiServ :APIservice) :RemoteData{
    override suspend fun getAllCryptoCurrencyList(): Response<CryptoCurrListResponse> {
        return apiServ.getAllCryptoCurrencyList()
    }

    override suspend fun getCryptoCurrencyLive(): Response<CryptoLiveExchangeRateResponse> {
        return apiServ.getCryptoCurrencyLive()
    }
}