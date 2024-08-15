package me.ponktacology.imageboard

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class BoardNotFoundAdvice {

    @ExceptionHandler(BoardNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun boardNotFoundHandler(ex: BoardNotFoundException): String? {
        return ex.message
    }
}