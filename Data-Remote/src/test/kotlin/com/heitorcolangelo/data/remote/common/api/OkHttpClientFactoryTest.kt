package com.heitorcolangelo.data.remote.common.api

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

private const val ELEMENTS = 5

class OkHttpClientFactoryTest {

    @Test
    fun `WHEN get client THEN should return OkHttpClient`() {
        val client = OkHttpClientFactory.getClient(getInterceptors(), null)

        assertEquals(ELEMENTS, client.interceptors.size)
    }

    @Test
    fun `WHEN is debug mode THEN level should be BODY`() {
        val interceptor = LoggingInterceptorFactory.getLoggingInterceptor(TestBuildConfiguration(true))

        assertTrue(interceptor.level == HttpLoggingInterceptor.Level.BODY)
    }

    @Test
    fun `WHEN is NOT debug mode THEN level should be NONE`() {
        val interceptor = LoggingInterceptorFactory.getLoggingInterceptor(TestBuildConfiguration(false))

        assertTrue(interceptor.level == HttpLoggingInterceptor.Level.NONE)
    }

    private fun getInterceptors(): List<TestInterceptor> {
        return mutableListOf<TestInterceptor>().also { list ->
            repeat(ELEMENTS) {
                list.add(TestInterceptor())
            }
        }
    }

    private class TestInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(Request.Builder().build())
        }
    }

    private class TestBuildConfiguration(private val isDebug: Boolean) : BuildConfiguration {
        override fun isDebug(): Boolean {
            return isDebug
        }

        override fun apiKey(): String {
            return "API_KEY"
        }

        override fun imageBaseUrl(): String {
            return "IMAGE_URL"
        }
    }
}
