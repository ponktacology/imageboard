package me.ponktacology.imageboard.service

import jakarta.servlet.http.HttpServletRequest
import me.ponktacology.imageboard.IpUtils
import me.ponktacology.imageboard.model.User
import me.ponktacology.imageboard.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getByRequest(request: HttpServletRequest): User {
        val ip = IpUtils.extract(request)
        return getByIp(ip)
    }

    fun getByIp(ip: String): User {
        return userRepository.findByUserName(ip) ?: userRepository.save(User(userName = ip))
    }
}