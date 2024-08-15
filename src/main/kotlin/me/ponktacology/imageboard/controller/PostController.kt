package me.ponktacology.imageboard.controller

import jakarta.servlet.http.HttpServletRequest
import me.ponktacology.imageboard.exception.BoardNotFoundException
import me.ponktacology.imageboard.exception.ThreadNotFoundException
import me.ponktacology.imageboard.dto.PostPutDTO
import me.ponktacology.imageboard.model.Post
import me.ponktacology.imageboard.repository.BoardRepository
import me.ponktacology.imageboard.repository.PostRepository
import me.ponktacology.imageboard.repository.ThreadRepository
import me.ponktacology.imageboard.service.PostService
import me.ponktacology.imageboard.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class PostController(
    private val threadRepository: ThreadRepository,
    private val boardRepository: BoardRepository,
    private val userService: UserService,
    private val postRepository: PostRepository,
    private val postService: PostService,
) {

    @GetMapping("/posts/{boardName}/{threadId}")
    fun getAll(@PathVariable boardName: String, @PathVariable threadId: Long): List<Post> {
        val board = boardRepository.findByName(boardName) ?: throw BoardNotFoundException(boardName)
        val thread =
            threadRepository.findByBoardAndId(board, threadId) ?: throw ThreadNotFoundException(board.name, threadId)
        return postRepository.findByThread(thread)
    }

    @PostMapping(
        "/posts/{boardName}/{threadId}",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun put(
        request: HttpServletRequest,
        @PathVariable boardName: String,
        @PathVariable threadId: Long,
        @RequestBody postDTO: PostPutDTO
    ): Post {
        val board = boardRepository.findByName(boardName) ?: throw BoardNotFoundException(boardName)
        val thread =
            threadRepository.findByBoardAndId(board, threadId) ?: throw ThreadNotFoundException(board.name, threadId)
        val user = userService.getByRequest(request)

        return postService.addPost(
            thread = thread,
            author = user,
            content = postDTO.content
        )
    }
}