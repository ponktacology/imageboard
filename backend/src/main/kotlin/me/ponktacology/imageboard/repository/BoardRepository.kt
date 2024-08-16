package me.ponktacology.imageboard.repository

import me.ponktacology.imageboard.model.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {

    fun findByName(name: String): Board?
}