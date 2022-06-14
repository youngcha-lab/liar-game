package com.youngcha.liargameapp.web

import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(val environment: Environment) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        println("environment : ${environment.activeProfiles.toList()}")
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:3000",
                "http://youngcha-liargame.tk:3000",
            )
            .allowedMethods(
                HttpMethod.OPTIONS.name,
                HttpMethod.HEAD.name,
                HttpMethod.POST.name,
                HttpMethod.PUT.name,
                HttpMethod.PATCH.name,
                HttpMethod.GET.name,
                HttpMethod.DELETE.name,
            )
            .allowCredentials(true)
    }
}
