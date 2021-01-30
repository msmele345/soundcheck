package com.mitchmele.soundcheck

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SoundcheckApplication

fun main(args: Array<String>) {
	runApplication<SoundcheckApplication>(*args)
}
