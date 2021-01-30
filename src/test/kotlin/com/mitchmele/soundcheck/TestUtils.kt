package com.mitchmele.soundcheck

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mitchmele.soundcheck.model.TradeCheckResponse
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

class TestUtils {

    companion object {

        fun testGet(pathVar: String?, expectation: TradeCheckResponse, mockMvc: MockMvc) {
            mockMvc.get("/api/v1/trades/${pathVar}") {
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { string(jacksonObjectMapper().writeValueAsString(expectation)) }
            }
        }

        fun testPost(input: Any, expectation: TradeCheckResponse, mockMvc: MockMvc) {
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
}