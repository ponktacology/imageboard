package me.ponktacology.imageboard.model

import jakarta.persistence.*

@Entity
@Table(name = "boards")
data class Board(
    @Id @GeneratedValue
    val id: Long = 0,
    var name: String = ""
)