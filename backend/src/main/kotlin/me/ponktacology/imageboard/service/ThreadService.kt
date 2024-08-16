package me.ponktacology.imageboard.service

import me.ponktacology.imageboard.model.Board
import me.ponktacology.imageboard.model.Content
import me.ponktacology.imageboard.model.User
import me.ponktacology.imageboard.repository.ThreadRepository
import org.springframework.stereotype.Service
import me.ponktacology.imageboard.model.Thread

@Service
class ThreadService(private val threadRepository: ThreadRepository) {

    fun createThread(board: Board, user: User, title: String, content: Content): Thread {
        return threadRepository.save(
            Thread(
                board = board,
                author = user,
                title = title,
                content = content
            )
        )
    }
}