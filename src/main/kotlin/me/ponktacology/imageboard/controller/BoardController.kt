package me.ponktacology.imageboard.controller

import me.ponktacology.imageboard.repository.BoardRepository
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(val boardRepository: BoardRepository) {


}