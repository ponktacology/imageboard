package me.ponktacology.imageboard.controller

import me.ponktacology.imageboard.BoardNotFoundException
import me.ponktacology.imageboard.ThreadDTO
import me.ponktacology.imageboard.model.Content
import me.ponktacology.imageboard.model.Thread
import me.ponktacology.imageboard.repository.BoardRepository
import me.ponktacology.imageboard.repository.ThreadRepository
import me.ponktacology.imageboard.repository.UserRepository
import me.ponktacology.imageboard.toDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ThreadLocalRandom

@RestController
class ThreadController(
    private val threadRepository: ThreadRepository,
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository
) {


    @GetMapping("/{board}/new")
    fun add(@PathVariable board: String): Thread {
        val board = boardRepository.findByName(board) ?: throw BoardNotFoundException(board)
        val content = "Ryczy z bólu ranny łoś ${ThreadLocalRandom.current().nextDouble()}"
        val user = userRepository.findAll().first()
        return threadRepository.save(
            Thread(
                board = board,
                author = user,
                title = "Lorem Ipsum",
                content = Content(content)
            )
        )
    }

    @GetMapping("/{name}")
    fun threads(@PathVariable name: String): List<ThreadDTO> {
        val board = boardRepository.findByName(name) ?: throw BoardNotFoundException(name)
        return threadRepository.findAllByBoard(board).map { it.toDto() }
    }
}