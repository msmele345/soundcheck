package com.mitchmele.soundcheck.service

import com.mitchmele.soundcheck.model.ExchangeProperties
import com.mitchmele.soundcheck.model.ExchangeResponse
import com.mitchmele.soundcheck.model.Trade
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate
import java.lang.StringBuilder
import kotlin.random.Random

class ExchangeClientTest {

    private val restResolver: RestResolver = mock()
    private val mockRestTemplate: RestTemplate = mock()
    private val mockProperties: ExchangeProperties = ExchangeProperties()

    private lateinit var subject: ExchangeClient

    @BeforeEach
    internal fun setUp() {
        subject = ExchangeClient(restResolver, mockProperties, mockRestTemplate)
    }

    @Test
    internal fun `getTradeDetails - delegates rest call to the rest handler`() {

        whenever(restResolver.send { any() }) doReturn getDefault("ABC")

        val symbol = getSymbol()

        println(symbol)

        val actual = subject.getTradeDetails("ABC")
    }

    private fun getDefault(symbol: String): ExchangeResponse {
        return ExchangeResponse(trades = listOf(Trade(
                symbol = getSymbol(),
                tradePrice = listOf(25.5, 21.6, 115.25).random()
        )))
    }

    fun getSymbol(): String {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        val stringBuilder = StringBuilder().apply {
            repeat(3) {
                append(alphabet[Random.nextInt(25)])
            }
        }
        return stringBuilder.toString().toUpperCase()
    }
}