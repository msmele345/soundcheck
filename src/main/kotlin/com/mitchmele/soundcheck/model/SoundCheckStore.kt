package com.mitchmele.soundcheck.model

import org.springframework.http.HttpStatus
import java.util.*

data class Trade(
        var id: Int? = null,
        val symbol: String? = null,
        val tradePrice: Double? = null,
        val timeStamp: Date? = null,
        val exchange: String? = null
)


data class TradeCheckResponse(val hasNewTrades: Boolean, val isNotFound: Boolean = false)

data class ExchangeResponse(val trades: List<Trade>)
data class ErrorResponse(val message: String = "", val status: String? = null)