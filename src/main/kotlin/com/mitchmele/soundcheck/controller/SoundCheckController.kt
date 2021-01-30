package com.mitchmele.soundcheck.controller

import com.mitchmele.soundcheck.model.TradeCheckResponse
import com.mitchmele.soundcheck.service.TradingService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class SoundCheckController(
        private val tradingService: TradingService
) {

    @RequestMapping("/trades/{symbol}",
            method = [RequestMethod.GET],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun check(@PathVariable symbol: String?): ResponseEntity<TradeCheckResponse> {
        return symbol?.let { ResponseEntity.ok().body(tradingService.checkForTrades(symbol.toUpperCase())) }
                ?: ResponseEntity.badRequest().build()
    }
}



