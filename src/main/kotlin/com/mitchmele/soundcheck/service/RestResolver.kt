package com.mitchmele.soundcheck.service

import com.mitchmele.soundcheck.model.ExchangeResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class RestResolver {

    fun send(call: () -> ResponseEntity<*>): ExchangeResponse {
        return call.invoke().run {
            when(statusCode) {
                HttpStatus.OK -> (this.body as ExchangeResponse)
                HttpStatus.NOT_FOUND -> throw EntityNotFoundException()
                else -> throw RuntimeException()
            }
        }
    }
}