package me.ponktacology.imageboard

import me.ponktacology.imageboard.model.*
import me.ponktacology.imageboard.repository.BoardRepository
import me.ponktacology.imageboard.repository.PostRepository
import me.ponktacology.imageboard.repository.ThreadRepository
import me.ponktacology.imageboard.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ThreadLocalRandom

@Configuration
class LoadDatabase {

    @Bean
    fun initDatabase(
        userRepository: UserRepository,
        boardRepository: BoardRepository,
        threadRepository: ThreadRepository,
        postRepository: PostRepository
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            val board = boardRepository.save(Board(name = "test"))
        }
    }

}