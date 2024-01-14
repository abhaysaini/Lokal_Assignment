package com.example.lokal_assignment.viewModels

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lokal_assignment.data.model.CryptoAPIResponse
import com.example.lokal_assignment.data.model.CryptoCurr
import com.example.lokal_assignment.data.repository.Repository
import com.example.lokal_assignment.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeoutException

class HomeViewModel(
    private val application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {
    private val handler = Handler(Looper.getMainLooper())

    val cryptoCurrencyList: MutableLiveData<Resource<List<CryptoCurr>>> = MutableLiveData()
    val lastUpdatedTime: MutableLiveData<String> = MutableLiveData("")

    init {
        refreshCryptoCurrency()
    }

    private fun refreshCryptoCurrency() {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoCurrencyList.postValue(Resource.Loading())
            try {
                val currencyList = repository.getCurrencyList().data
                val exchangeRates = repository.getLiveExchangeRates().data
                val combinedList: List<CryptoCurr> = combineLists(currencyList, exchangeRates)
                cryptoCurrencyList.postValue(Resource.Success(combinedList))
                updateLastUpdateTime()
                handler.postDelayed({ refreshCryptoCurrency() }, 200000L)
            } catch (e: Exception) {
                cryptoCurrencyList.postValue(Resource.Error(e.message.toString()))
                if (e is TimeoutException) refreshCryptoCurrency()
            }
        }
    }

    fun reloadCryptoCurrency() {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoCurrencyList.postValue(Resource.Loading())
            try {
                val currencyList = repository.getCurrencyList().data
                val exchangeRates = repository.getLiveExchangeRates().data
                val combinedList: List<CryptoCurr> = combineLists(currencyList, exchangeRates)
                cryptoCurrencyList.postValue(Resource.Success(combinedList))
                updateLastUpdateTime()
            } catch (e: Exception) {
                cryptoCurrencyList.postValue(Resource.Error(e.message.toString()))
                if (e is TimeoutException) reloadCryptoCurrency()
            }
        }
    }

    private fun updateLastUpdateTime() {
        lastUpdatedTime.postValue(getCurrentTime())
    }

    private fun combineLists(
        CurrencyList: Map<String, CryptoAPIResponse>?,
        exchangeRates: Map<String, Double>?
    ): List<CryptoCurr> {
        return CurrencyList.orEmpty()
            .entries
            .mapNotNull { entry ->
                val currencyCode = entry.key
                val cryptoCurrencyListApiData = entry.value
                val exchangeRate = exchangeRates?.get(currencyCode)
                exchangeRate?.let {
                    CryptoCurr(
                        image_url = cryptoCurrencyListApiData.iconUrl,
                        crypto_rate = it,
                        name = cryptoCurrencyListApiData.name,
                        full_name = cryptoCurrencyListApiData.fullName,
                        symbol = cryptoCurrencyListApiData.symbol
                    )
                }
            }
    }


    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss")
        val currentTimeMillis = System.currentTimeMillis()
        val date = Date(currentTimeMillis)
        return dateFormat.format(date)
    }
}