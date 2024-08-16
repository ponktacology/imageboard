package me.ponktacology.imageboard

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Apply CORS to all paths
            .allowedOrigins("*") // Allows all origins
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
            .allowedHeaders("*") // Allows all headers
    }
}