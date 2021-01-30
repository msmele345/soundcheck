package com.mitchmele.soundcheck.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mitchmele.soundcheck.advice.RestAdvice
import com.mitchmele.soundcheck.model.ErrorResponse
import com.mitchmele.soundcheck.model.TradeCheckResponse
import com.mitchmele.soundcheck.service.TradingService
import com.nhaarman.mockito_kotlin.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.function.RequestPredicates.contentType
import javax.persistence.EntityNotFoundException

class RestAdviceTest {

    private val mockService: TradingService = mock()

    lateinit var subject: SoundCheckController

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        subject = SoundCheckController(mockService)
        mockMvc = MockMvcBuilders
                .standaloneSetup(subject)
                .setControllerAdvice(RestAdvice())
                .build()
    }

    @Test
    fun `EntityNotFoundException - returns 404`() {

        val expectedErrorResponse = ErrorResponse(message = "Symbol Not Found")

        whenever(mockService.checkForTrades(any())) doThrow EntityNotFoundException("Symbol Not Found")

        mockMvc.get("/api/v1/trades/v") {
            content =  contentType(MediaType.APPLICATION_JSON)
        }.andExpect {
                    status { isNotFound() }
                    content { string(jacksonObjectMapper().writeValueAsString(expectedErrorResponse)) }
                }
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
