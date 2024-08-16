package me.ponktacology.imageboard.dto

import me.ponktacology.imageboard.model.Content

data class ThreadPutDTO(
    val title: String,
    val content: Content
)