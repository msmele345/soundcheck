package com.mitchmele.soundcheck.model

import java.util.*

data class Trade(
        var id: Int? = null,
        val symbol: String? = null,
        val tradePrice: Double? = null,
        val timeStamp: Date? = null,
        val exchange: String? = null
)


data class TradeCheckResponse(val hasNewTrades: Boolean)

data class ExchangeResponse(val trades: List<Trade>)