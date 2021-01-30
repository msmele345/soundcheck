package com.mitchmele.soundcheck.advice

import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig(
        private val restTemplateBuilder: RestTemplateBuilder
) {

    @Bean
    fun interstellarRestTemplate(): RestTemplate {

        val client: CloseableHttpClient = HttpClientBuilder
                .create()
                .build();

        return restTemplateBuilder
                .requestFactory { HttpComponentsClientHttpRequestFactory(client) }.build()
    }
}