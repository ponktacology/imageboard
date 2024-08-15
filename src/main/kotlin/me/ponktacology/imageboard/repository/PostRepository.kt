package me.ponktacology.imageboard.repository

import me.ponktacology.imageboard.model.Post
import me.ponktacology.imageboard.model.Thread
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {

    fun findByThread(thread: Thread): List<Post>
}