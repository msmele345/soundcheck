package com.mitchmele.soundcheck.service

import com.mitchmele.soundcheck.model.ExchangeResponse
import com.mitchmele.soundcheck.model.Trade
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import javax.persistence.EntityNotFoundException

class RestResolverTest {

    private val subject = RestResolver()

    @Test
    internal fun `send - executes call and extracts response entity metadata`() {

        val mockCall: () -> ResponseEntity<*> = mock()

        val exchangeResponse = ExchangeResponse(
                listOf(Trade(
                        symbol = "ABC",
                        tradePrice = 20.01
                )))
        val response: ResponseEntity<ExchangeResponse> = ResponseEntity
                .ok()
                .body(exchangeResponse)

        whenever(mockCall.invoke()) doReturn response

        val actual = subject.send { mockCall() }

        assertThat(actual).isEqualTo(exchangeResponse)
    }

    @Test
    internal fun `send - throws EntityNotFoundException if exchange response is 404 Not Found`() {

        val mockCall: () -> ResponseEntity<*> = mock()

        val response: ResponseEntity<*> = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)

        whenever(mockCall.invoke()) doReturn response

        assertThatThrownBy {
            subject.send { mockCall() }
        }.isInstanceOf(EntityNotFoundException::class.java)
    }
}