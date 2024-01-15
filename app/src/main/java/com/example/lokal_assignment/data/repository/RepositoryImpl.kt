package com.example.lokal_assignment.data.repository

import android.content.Context
import com.example.lokal_assignment.data.model.CryptoAPIResponse
import com.example.lokal_assignment.data.repository.datasource.CacheData
import com.example.lokal_assignment.data.repository.datasource.LocalData
import com.example.lokal_assignment.data.repository.datasource.RemoteData
import com.example.lokal_assignment.utils.Resource
import com.example.lokal_assignment.utils.isNetworkAvailable


class RepositoryImpl(
    private val applicationContext: Context,
    private val cacheData: CacheData,
    private val localData: LocalData,
    private val remoteData: RemoteData,
) : Repository {

    override suspend fun getCurrencyList(): Resource<Map<String, CryptoAPIResponse>> {
        if(isNetworkAvailable(applicationContext)){
            val cryptoCurrencyListMap = cacheData.getCryptoCurrencyListFromCache()
            if(cryptoCurrencyListMap.size > 0) return Resource.Success(cryptoCurrencyListMap)

            var response = remoteData.getAllCryptoCurrencyList()
            if(response.isSuccessful){
                response.body()?.let{result->
                    cacheData.saveCryptoCurrencyListToCache(result.crypto)
                    return Resource.Success(result.crypto)
                }
            }else{
                return Resource.Error(response.message())
            }
        }
        throw IllegalStateException("Internet is not available")
        return Resource.Error("Internet is not available")
    }

    override suspend fun getLiveExchangeRates(): Resource<Map<String, Double>> {
        if(isNetworkAvailable(applicationContext)){
            var response = remoteData.getCryptoCurrencyLive()
            if(response.isSuccessful){
                response.body()?.let{result->
                    return Resource.Success(result.rates)
                }
            }else{
                return Resource.Error(response.message())
            }
        }
        throw IllegalStateException("Internet is not available")
        return Resource.Error("Internet is not available")
    }
}