package me.ponktacology.imageboard.repository

import me.ponktacology.imageboard.model.Board
import me.ponktacology.imageboard.model.Thread
import org.springframework.data.jpa.repository.JpaRepository

interface ThreadRepository : JpaRepository<Thread, Long> {

    fun findAllByBoard(board: Board): List<Thread>
}