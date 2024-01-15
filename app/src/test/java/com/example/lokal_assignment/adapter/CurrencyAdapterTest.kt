package com.example.lokal_assignment.adapter
import android.view.ViewGroup
import com.example.lokal_assignment.data.model.CryptoCurr
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import android.content.Context

class CurrencyAdapterTest {

    @Test
    fun `roundTo6DecimalPlaces should round the value to 6 decimal places`() {
        val context = mock(Context::class.java)

        // Arrange
        val currencyAdapter = CurrencyAdapter(context, emptyList(), {})

        // Act
        val roundedValue = currencyAdapter.roundTo6DecimalPlaces(12.3456789)

        // Assert
        assertEquals("12.345679", roundedValue)
    }



    interface CryptoCurrListener {
        fun invoke(cryptoCurr: CryptoCurr)
        fun CryptoCurrListener.toLambda(): (CryptoCurr) -> Unit = { cryptoCurr -> invoke(cryptoCurr) }
    }

    @Test
    fun `onBindViewHolder should bind data correctly`() {
        // Arrange
        val context = mock(Context::class.java)
        val cryptoCurrList = listOf(CryptoCurr("Bitcoin", 20.00, "Bitcoin Icon URL", "", ""))
        val listener = mock(CryptoCurrListener::class.java)
        val currencyAdapter = CurrencyAdapter(context, cryptoCurrList, listener)
        val parent = mock(ViewGroup::class.java)
        val viewType = 0

        val viewHolder = currencyAdapter.onCreateViewHolder(parent, viewType)

        currencyAdapter.onBindViewHolder(viewHolder, 0)

        assertEquals("Bitcoin", viewHolder.binding.cryptoName.text.toString())
        assertEquals("BTC", viewHolder.binding.cryptoSymbol.text.toString())
        assertEquals("50000.000000 $", viewHolder.binding.cryptoValue.text.toString())

        viewHolder.binding.root.performClick()
        verify(listener).invoke(any(CryptoCurr::class.java))
    }

    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)
}
