package com.mitchmele.soundcheck.service

import com.mitchmele.soundcheck.model.ExchangeResponse
import com.mitchmele.soundcheck.model.Trade
import com.mitchmele.soundcheck.model.TradeCheckResponse
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class TradingServiceTest {

    private val mockExchangeClient: ExchangeClient = mock()

    private val subject: TradingService = TradingService(mockExchangeClient)

    @Test
    fun `checkForTrades - calls client to get response from exchange`() {

        val exchangeResponse = ExchangeResponse(
                listOf(Trade(
                        symbol = "ABC",
                        tradePrice = 20.01
                )))
        val response: ResponseEntity<ExchangeResponse> = ResponseEntity.ok().body(exchangeResponse)
        whenever(mockExchangeClient.getTradeDetails(any())) doReturn response

        val expected = TradeCheckResponse(true)

        val actual = subject.checkForTrades("ABC")
        assertThat(actual).isEqualTo(expected)

        verify(mockExchangeClient).getTradeDetails("ABC")
    }

    @Test
    fun `checkForTrades - returns response with FALSE if response contains no trades`() {

        val exchangeResponse = ExchangeResponse(emptyList())
        val response: ResponseEntity<ExchangeResponse> = ResponseEntity.ok().body(exchangeResponse)
        whenever(mockExchangeClient.getTradeDetails(any())) doReturn response

        val expected = TradeCheckResponse(false)

        val actual = subject.checkForTrades("ABC")
        assertThat(actual).isEqualTo(expected)
    }
}
