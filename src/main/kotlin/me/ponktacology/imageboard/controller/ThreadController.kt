package me.ponktacology.imageboard.controller

import jakarta.servlet.http.HttpServletRequest
import me.ponktacology.imageboard.exception.BoardNotFoundException
import me.ponktacology.imageboard.exception.ThreadNotFoundException
import me.ponktacology.imageboard.dto.ThreadPutDTO
import me.ponktacology.imageboard.model.Thread
import me.ponktacology.imageboard.repository.BoardRepository
import me.ponktacology.imageboard.repository.ThreadRepository
import me.ponktacology.imageboard.service.ThreadService
import me.ponktacology.imageboard.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/threads")
class ThreadController(
    private val threadRepository: ThreadRepository,
    private val boardRepository: BoardRepository,
    private val userService: UserService,
    private val threadService: ThreadService,
) {

    @GetMapping("/get/{id}")
    fun get(
        @PathVariable boardName: String,
        @PathVariable id: Long
    ): Thread {
        val board = boardRepository.findByName(boardName) ?: throw BoardNotFoundException(boardName)
        return threadRepository.findByBoardAndId(board, id) ?: throw ThreadNotFoundException(board.name, id)
    }

    @GetMapping("/all/{boardName}")
    fun all(@PathVariable boardName: String): List<Thread> {
        val board = boardRepository.findByName(boardName) ?: throw BoardNotFoundException(boardName)
        return threadRepository.findAllByBoard(board)
    }

    @PostMapping(
        "/put/{boardName}",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun put(
        request: HttpServletRequest,
        @PathVariable boardName: String,
        @RequestBody threadDTO: ThreadPutDTO
    ): Thread {
        val board = boardRepository.findByName(boardName) ?: throw BoardNotFoundException(boardName)
        val user = userService.getByRequest(request)

        return threadService.createThread(
            board,
            user,
            threadDTO.title,
            threadDTO.content
        )
    }
}