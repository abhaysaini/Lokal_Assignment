package com.example.lokal_assignment.viewModels

import android.app.Application
import androidx.lifecycle.Observer
import com.example.lokal_assignment.data.model.CryptoAPIResponse
import com.example.lokal_assignment.data.model.CryptoCurr
import com.example.lokal_assignment.data.repository.Repository
import com.example.lokal_assignment.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.any
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.argThat
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.verify


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var cryptoCurrencyObserver: Observer<Resource<List<CryptoCurr>>>

    @Mock
    private lateinit var lastUpdatedTimeObserver: Observer<String>

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(application, repository)

        viewModel.cryptoCurrencyList.observeForever(cryptoCurrencyObserver)
        viewModel.lastUpdatedTime.observeForever(lastUpdatedTimeObserver)
    }

    @After
    fun tearDown() {
        viewModel.cryptoCurrencyList.removeObserver(cryptoCurrencyObserver)
        viewModel.lastUpdatedTime.removeObserver(lastUpdatedTimeObserver)
    }

    @Test
    fun `refreshCryptoCurrency should post Loading and then Success`() {
        runBlocking {
            // Arrange
            val currencyList = mockCurrencyList()
            val exchangeRates = mockExchangeRates()
            `when`(repository.getCurrencyList()).thenReturn(Resource.Success(currencyList))
            `when`(repository.getLiveExchangeRates()).thenReturn(Resource.Success(exchangeRates))

            // Act
            viewModel.refreshCryptoCurrency()
            delay(100) // Let the coroutine finish

            // Assert
            verify(cryptoCurrencyObserver).onChanged(Resource.Loading())
            verify(cryptoCurrencyObserver).onChanged(argThat { it is Resource.Success && it.data is List<CryptoCurr> })

        }
    }

    fun mockCurrencyList(): Map<String, CryptoAPIResponse> {
        return mapOf(
            "BTC" to CryptoAPIResponse("Bitcoin", "BTC", "Bitcoin Icon URL",""),
            "ETH" to CryptoAPIResponse("Ethereum", "ETH", "Ethereum Icon URL",""),
        )
    }

    fun mockExchangeRates(): Map<String, Double> {
        return mapOf(
            "BTC" to 50000.0,
            "ETH" to 2000.0,
        )
    }

}
