package me.ponktacology.imageboard.controller

import me.ponktacology.imageboard.model.Board
import me.ponktacology.imageboard.repository.BoardRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/boards")
class BoardController(val boardRepository: BoardRepository) {

    @GetMapping("/all")
    fun all(): List<Board> {
        return boardRepository
            .findAll()
    }
}