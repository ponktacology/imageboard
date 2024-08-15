package me.ponktacology.imageboard

import me.ponktacology.imageboard.model.Content
import me.ponktacology.imageboard.model.Thread

data class ThreadDTO(
    val author: String,
    val title: String,
    val content: Content
)

fun Thread.toDto() = ThreadDTO(this.author!!.userName, this.title, this.content!!)