package com.mitchmele.soundcheck.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties("exchange")
data class ExchangeProperties(
        var url: String? = null,
        val apiKey: String? = null
)