package com.example.lokal_assignment.data.repository.datasource

import com.example.lokal_assignment.data.model.CryptoAPIResponse

interface CacheData {
    suspend fun saveCryptoCurrencyListToCache(cryptoCurrencyListMap : Map<String, CryptoAPIResponse> )
    suspend fun getCryptoCurrencyListFromCache() : Map<String, CryptoAPIResponse>
}