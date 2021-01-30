package com.mitchmele.soundcheck.advice

import com.mitchmele.soundcheck.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.persistence.EntityNotFoundException

@RestControllerAdvice
class RestAdvice {

    //finish service test for 404
    //setup RestTemplate config for client
    //finish client

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFound(exception: EntityNotFoundException): ErrorResponse =
            ErrorResponse(message = exception.localizedMessage)

}