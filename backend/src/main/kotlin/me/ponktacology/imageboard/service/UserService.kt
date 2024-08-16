package me.ponktacology.imageboard.service

import jakarta.servlet.http.HttpServletRequest
import me.ponktacology.imageboard.IpUtils
import me.ponktacology.imageboard.exception.InvalidIpAddressException
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
        // TODO: Make additional check for valid IPv4 or IPv6 format
        if (ip.isBlank()) throw InvalidIpAddressException(ip)
        return userRepository.findByUserName(ip) ?: userRepository.save(User(userName = ip))
    }
}