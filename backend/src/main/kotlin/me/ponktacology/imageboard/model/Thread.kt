package me.ponktacology.imageboard.model

import jakarta.persistence.*

@Entity
@Table(name = "threads")
data class Thread(
    @Id @GeneratedValue
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    val board: Board? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var author: User? = null,

    val timeStamp: Long = System.currentTimeMillis(),
    var title: String = "",
    var content: Content? = null,
)