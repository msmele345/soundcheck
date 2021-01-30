package com.mitchmele.soundcheck.service

import com.mitchmele.soundcheck.model.ExchangeProperties
import com.mitchmele.soundcheck.model.ExchangeResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ExchangeClient(
        val exchangeProperties: ExchangeProperties
        //restResolver or error handler that takes a function to resolve to ResponseEntity
) {

    fun getTradeDetails(symbol: String): ResponseEntity<ExchangeResponse> {
        return ResponseEntity.badRequest().build();
    }
}
