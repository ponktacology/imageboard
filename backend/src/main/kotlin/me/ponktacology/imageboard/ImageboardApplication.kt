package me.ponktacology.imageboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ImageBoardApplication

fun main(args: Array<String>) {
	runApplication<ImageBoardApplication>(*args)
}
