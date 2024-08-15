package me.ponktacology.imageboard.service

import me.ponktacology.imageboard.model.User
import me.ponktacology.imageboard.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getByIp(ip: String): User {
        return userRepository.findByUserName(ip) ?: userRepository.save(User(userName = ip))
    }
}