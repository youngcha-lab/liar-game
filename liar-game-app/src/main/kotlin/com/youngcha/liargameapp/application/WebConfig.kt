package com.youngcha.liargameapp.application

import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(val environment: Environment) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginIfLocal(environment, "http://localhost:3000")
            .allowedOrigins("http://youngcha-liargame.ml:3000")
            .allowedMethods(
                HttpMethod.OPTIONS.name,
                HttpMethod.HEAD.name,
                HttpMethod.POST.name,
                HttpMethod.PUT.name,
                HttpMethod.PATCH.name,
                HttpMethod.GET.name,
                HttpMethod.DELETE.name,
            )
    }
}

fun CorsRegistration.allowedOriginIfLocal(environment: Environment, origin: String): CorsRegistration =
    if (environment.acceptsProfiles(Profiles.of("local"))) {
        this.allowedOrigins(origin)
    } else this
