package com.example.lokal_assignment.data.repository

import com.example.lokal_assignment.data.model.CryptoAPIResponse
import com.example.lokal_assignment.utils.Resource

interface Repository {
    suspend fun getCurrencyList() : Resource<Map<String, CryptoAPIResponse>>
    suspend fun getLiveExchangeRates() : Resource<Map<String, Double>>
}