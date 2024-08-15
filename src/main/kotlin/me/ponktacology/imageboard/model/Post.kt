package me.ponktacology.imageboard.model

import jakarta.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id @GeneratedValue
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var author: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    var board: Board? = null,

    val timeStamp: Long = System.currentTimeMillis(),
    var content: Content? = null
)