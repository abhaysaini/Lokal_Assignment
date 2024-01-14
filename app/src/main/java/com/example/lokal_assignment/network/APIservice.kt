package com.example.lokal_assignment.network

import com.example.lokal_assignment.BuildConfig
import com.example.lokal_assignment.data.model.CryptoCurrListResponse
import com.example.lokal_assignment.data.model.CryptoLiveExchangeRateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIservice {


    @GET("list")
    suspend fun getAllCryptoCurrencyList(
        @Query("access_key")
        accessKey:String  =  BuildConfig.API_KEY
    ) : Response<CryptoCurrListResponse>

    @GET("live")
    suspend fun getCryptoCurrencyLive(
        @Query("access_key")
        accessKey:String = BuildConfig.API_KEY
    ) : Response<CryptoLiveExchangeRateResponse>
}