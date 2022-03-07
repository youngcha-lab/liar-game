package com.youngcha.liargameapp.`in`.cors

import org.springframework.http.HttpHeaders
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsProcessor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LocalWebViewCorsProcessor(private val delegate: CorsProcessor) : CorsProcessor {

    override fun processRequest(
        configuration: CorsConfiguration?,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Boolean {
        if (isLocalWebViewRequest(request)) {
//            logger.debug("Ignoring cors process for local-webview request")
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, DEFAULT_ORIGIN)
        }
        return delegate.processRequest(configuration, request, response)
    }

    private fun isLocalWebViewRequest(request: HttpServletRequest): Boolean {
        return DEFAULT_ORIGIN == request.getHeader(HttpHeaders.ORIGIN)
    }

    //    companion object : KLogging() {
    companion object {
        const val DEFAULT_ORIGIN = "null"
    }
}
