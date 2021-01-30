package com.mitchmele.soundcheck.service

import com.mitchmele.soundcheck.model.ExchangeProperties
import com.mitchmele.soundcheck.model.ExchangeResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ExchangeClient(
        private val restResolver: RestResolver,
        private val exchangeProperties: ExchangeProperties,
        private val interstellarRestTemplate: RestTemplate,
        //restResolver or error handler that takes a function to resolve to ResponseEntity
) {

    fun getTradeDetails(symbol: String): ExchangeResponse? {
        //getForEntity
        return null
    }

}
