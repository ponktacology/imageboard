/*
package me.ponktacology.imageboard

import jakarta.servlet.http.HttpServletRequest
import me.ponktacology.imageboard.exception.InvalidIpAddressException
import me.ponktacology.imageboard.model.User
import me.ponktacology.imageboard.repository.UserRepository
import me.ponktacology.imageboard.service.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class UserTests {

    @Autowired
    private lateinit var userService: UserService

    @MockBean
    private lateinit var userRepository: UserRepository

    @Test
    fun `should return user by request`() {
        val ipAddress = "127.0.0.1"
        val request = Mockito.mock(HttpServletRequest::class.java)
        val mockUser = User(userName = ipAddress)

        `when`(request.remoteAddr).thenReturn(ipAddress)
        `when`(userRepository.findByUserName(ipAddress)).thenReturn(mockUser)

        val result = userService.getByRequest(request)

        assertNotNull(result)
        assertEquals(mockUser.userName, result.userName)
    }

    @Test
    fun `should save and return new user if user does not exist`() {
        val ipAddress = "127.0.0.1"
        val request = Mockito.mock(HttpServletRequest::class.java)
        val mockUser = User(userName = ipAddress)

        `when`(request.remoteAddr).thenReturn(ipAddress)
        `when`(userRepository.findByUserName(ipAddress)).thenReturn(null)
        `when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(mockUser)

        val result = userService.getByRequest(request)

        assertNotNull(result)
        assertEquals(ipAddress, result.userName)
    }

    @Test
    fun `should throw InvalidIpAddressException for blank IP`() {
        val blankIpAddress = ""

        assertThrows(InvalidIpAddressException::class.java) {
            userService.getByIp(blankIpAddress)
        }
    }

    @Test
    fun `should return user by IP`() {
        val ipAddress = "127.0.0.1"
        val mockUser = User(userName = ipAddress)

        `when`(userRepository.findByUserName(ipAddress)).thenReturn(mockUser)

        val result = userService.getByIp(ipAddress)

        assertNotNull(result)
        assertEquals(mockUser.userName, result.userName)
    }

    @Test
    fun `should save and return new user if IP does not exist`() {
        val ipAddress = "192.168.0.1"
        val mockUser = User(userName = ipAddress)

        `when`(userRepository.findByUserName(ipAddress)).thenReturn(null)
        `when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(mockUser)

        val result = userService.getByIp(ipAddress)

        assertNotNull(result)
        assertEquals(mockUser.userName, result.userName)
    }
}

 */