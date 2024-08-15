package me.ponktacology.imageboard.repository

import me.ponktacology.imageboard.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>  {
}