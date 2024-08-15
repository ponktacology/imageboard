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
class ThreadController(
    private val threadRepository: ThreadRepository,
    private val boardRepository: BoardRepository,
    private val userService: UserService,
    private val threadService: ThreadService,
) {

    @GetMapping("/threads/{id}")
    fun get(
        @PathVariable boardName: String,
        @PathVariable id: Long
    ): Thread {
        val board = boardRepository.findByName(boardName) ?: throw BoardNotFoundException(boardName)
        return threadRepository.findByBoardAndId(board, id) ?: throw ThreadNotFoundException(board.name, id)
    }

    @GetMapping("/threads/{boardName}/all")
    fun getAll(@PathVariable boardName: String): List<Thread> {
        val board = boardRepository.findByName(boardName) ?: throw BoardNotFoundException(boardName)
        return threadRepository.findAllByBoard(board)
    }

    @PostMapping(
        "/threads/{boardName}",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun add(
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

    @GetMapping("/threads/{name}")
    fun threads(@PathVariable name: String): List<Thread> {
        val board = boardRepository.findByName(name) ?: throw BoardNotFoundException(name)
        return threadRepository.findAllByBoard(board)
    }
}