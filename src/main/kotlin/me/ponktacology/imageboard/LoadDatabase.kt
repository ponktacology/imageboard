package me.ponktacology.imageboard

import me.ponktacology.imageboard.model.Board
import me.ponktacology.imageboard.model.Content
import me.ponktacology.imageboard.model.Thread
import me.ponktacology.imageboard.model.User
import me.ponktacology.imageboard.repository.BoardRepository
import me.ponktacology.imageboard.repository.ThreadRepository
import me.ponktacology.imageboard.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoadDatabase {

    @Bean
    fun initDatabase(
        userRepository: UserRepository,
        boardRepository: BoardRepository,
        threadRepository: ThreadRepository
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            val user = userRepository.save(User(userName = "ponkta"))
            val board = boardRepository.save(Board(name = "test"))
            val thread = Thread(author = user, board = board, title = "Example Thread", content = Content("Siema eniu!"))
            threadRepository.save(thread)
        }
    }

}