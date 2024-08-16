package me.ponktacology.imageboard.service

import me.ponktacology.imageboard.model.Content
import me.ponktacology.imageboard.model.Post
import me.ponktacology.imageboard.model.Thread
import me.ponktacology.imageboard.model.User
import me.ponktacology.imageboard.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {


    fun addPost(thread: Thread, author: User, content: Content): Post {
        return postRepository.save(
            Post(
                author = author,
                thread = thread,
                content = content
            )
        )
    }
}