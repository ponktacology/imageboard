package me.ponktacology.imageboard.service

import me.ponktacology.imageboard.model.Board
import me.ponktacology.imageboard.model.Content
import me.ponktacology.imageboard.model.Post
import me.ponktacology.imageboard.model.User
import me.ponktacology.imageboard.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {

    fun addPost(board: Board, user: User, content: Content): Post {
        return postRepository.save(Post(author = user, board = board, content = content))
    }
}