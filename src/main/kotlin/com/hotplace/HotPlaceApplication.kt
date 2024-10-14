package com.hotplace

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HotPlaceApplication

fun main(args: Array<String>) {
    runApplication<HotPlaceApplication>(*args)
}
