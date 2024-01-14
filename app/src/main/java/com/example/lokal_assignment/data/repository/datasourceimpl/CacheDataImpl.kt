package com.example.lokal_assignment.data.repository.datasourceimpl

import com.example.lokal_assignment.data.model.CryptoAPIResponse
import com.example.lokal_assignment.data.repository.datasource.CacheData

class CacheDataImpl : CacheData {

    private var cryptoCurrencyListMapCache = mutableMapOf<String, CryptoAPIResponse>()
    override suspend fun saveCryptoCurrencyListToCache(cryptoCurrencyListMap: Map<String, CryptoAPIResponse>) {
        cryptoCurrencyListMapCache.clear()
        cryptoCurrencyListMapCache.putAll(cryptoCurrencyListMap)
    }

    override suspend fun getCryptoCurrencyListFromCache(): Map<String, CryptoAPIResponse> {
        return cryptoCurrencyListMapCache
    }

}