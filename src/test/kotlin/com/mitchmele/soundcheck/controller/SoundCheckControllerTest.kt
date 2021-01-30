package com.mitchmele.soundcheck.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mitchmele.soundcheck.TestUtils
import com.mitchmele.soundcheck.model.TradeCheckResponse
import com.mitchmele.soundcheck.service.TradingService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class SoundCheckControllerTest {

    private val mockService: TradingService = mock()

    lateinit var subject: SoundCheckController

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        subject = SoundCheckController(mockService)
        mockMvc = MockMvcBuilders.standaloneSetup(subject).build()
    }

    @Test
    fun `consumes path variable and calls service with symbol`() {

        val expected = TradeCheckResponse(true)
        whenever(mockService.checkForTrades(any())).thenReturn(expected);

        mockMvc.get("/api/v1/trades/abc") {
            contentType = MediaType.APPLICATION_JSON
        }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    content { string(jacksonObjectMapper().writeValueAsString(expected)) }
                }

        verify(mockService).checkForTrades("ABC")
    }


    private fun testGet(pathVar: String?, expectation: TradeCheckResponse) {
        mockMvc.get("/api/v1/trades/${pathVar}") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { string(jacksonObjectMapper().writeValueAsString(expectation)) }
        }
    }

    private fun testPost(input: Any, expectation: TradeCheckResponse) {
        mockMvc.post("/api/v1/trades") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(input)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { string(jacksonObjectMapper().writeValueAsString(expectation)) }
        }
    }
}
