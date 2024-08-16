package me.ponktacology.imageboard.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue
    val id: Long = 0,
    val userName: String = ""
)