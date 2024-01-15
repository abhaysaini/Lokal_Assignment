package com.example.lokal_assignment.network

import org.junit.jupiter.api.Assertions.*

import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class NetModuleTest {

    @Test
    fun `provideInterceptor should return an instance of HttpLoggingInterceptor with the correct level`() {
        // Arrange
        val netModule = NetModule()

        // Act
        val interceptor = netModule.provideInterceptor()

        // Assert
        assert(interceptor.level == HttpLoggingInterceptor.Level.BODY)
    }
}
