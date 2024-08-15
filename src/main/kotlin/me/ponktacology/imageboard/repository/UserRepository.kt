package me.ponktacology.imageboard.repository

import me.ponktacology.imageboard.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByUserName(username: String): User?
}