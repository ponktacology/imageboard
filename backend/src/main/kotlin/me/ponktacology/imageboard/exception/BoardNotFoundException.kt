package me.ponktacology.imageboard.exception

class BoardNotFoundException(name: String) : RuntimeException("Board $name not found")