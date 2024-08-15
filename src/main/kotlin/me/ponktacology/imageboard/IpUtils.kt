package me.ponktacology.imageboard

import jakarta.servlet.http.HttpServletRequest

object IpUtils {

    fun extract(request: HttpServletRequest): String {
        val ipAddress = request.getHeader("X-Forwarded-For")

        return if (ipAddress.isNullOrEmpty() || ipAddress == "unknown") {
            request.remoteAddr
        } else {
            ipAddress.split(",")[0];
        }
    }
}