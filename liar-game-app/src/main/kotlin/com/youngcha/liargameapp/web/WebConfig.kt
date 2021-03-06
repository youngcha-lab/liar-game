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
        registry
            .addMapping("/**")
            .allowedOrigins(
                "http://localhost:3000",
                "http://youngcha-liargame.tk:3000",
                "http://youngcha-liargame.tk",
                "https://youngcha-liargame.tk",
                "http://liargame.duckdns.org:3000",
                "http://liargame.duckdns.org",
                "https://liargame.duckdns.org",
            ).allowedMethods(
                HttpMethod.GET.name,
                HttpMethod.HEAD.name,
                HttpMethod.POST.name,
                HttpMethod.PUT.name,
                HttpMethod.PATCH.name,
                HttpMethod.DELETE.name,
                HttpMethod.OPTIONS.name,
                HttpMethod.TRACE.name,
            )
            .allowCredentials(true)
    }
}
