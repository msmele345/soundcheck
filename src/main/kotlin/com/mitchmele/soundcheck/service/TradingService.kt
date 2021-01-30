package com.mitchmele.soundcheck.service

import com.mitchmele.soundcheck.model.Trade
import com.mitchmele.soundcheck.model.TradeCheckResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TradingService(
        private val exchangeClient: ExchangeClient
) {

    fun checkForTrades(symbol: String): TradeCheckResponse {
        //just need transformer from exchangeResponse>TradeCheckResponse
//        return exchangeClient.getTradeDetails(symbol).run {
//            when (statusCode) {
//                HttpStatus.OK -> {
//                    val trades: Int = this.body?.trades?.size ?: 0
//                    if (trades > 0) TradeCheckResponse(true) else TradeCheckResponse(false)
//                }
//                HttpStatus.NOT_FOUND -> {
//                    //TODO //throw EntityNotFoundException for Rest Advice
//                    TradeCheckResponse(false)
//                }
//                else -> TradeCheckResponse(false)
//            }
//        }
        return TradeCheckResponse(false)
    }
}
