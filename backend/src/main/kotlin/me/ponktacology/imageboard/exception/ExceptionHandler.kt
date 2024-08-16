package me.ponktacology.imageboard.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BoardNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun boardNotFoundHandler(ex: BoardNotFoundException): String? {
        return ex.message
    }

    @ExceptionHandler(ThreadNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun threadNotFoundHandler(ex: ThreadNotFoundException): String? {
        return ex.message
    }
}