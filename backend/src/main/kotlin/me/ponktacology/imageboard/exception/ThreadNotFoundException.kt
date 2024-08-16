package me.ponktacology.imageboard.exception

class ThreadNotFoundException(board: String, id: Long) :
    RuntimeException("Thread on board $board with id $id not found")