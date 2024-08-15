package me.ponktacology.imageboard

class BoardNotFoundException(name: String) : RuntimeException("Board $name not found")