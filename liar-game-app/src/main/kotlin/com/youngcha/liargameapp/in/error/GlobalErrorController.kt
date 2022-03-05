package com.youngcha.liargameapp.`in`.error

import org.springframework.boot.autoconfigure.web.ErrorProperties
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("\${server.error.path:\${error.path:/error}}")
class GlobalErrorController(
    val errorAttributes: ErrorAttributes,
    val serverProperties: ServerProperties
) : AbstractErrorController(errorAttributes, emptyList()) {

    val errorProperties: ErrorProperties
        get() = serverProperties.error

    @RequestMapping
    fun error(request: HttpServletRequest): ResponseEntity<Map<String, Any>> {
        val body = getErrorAttributes(request, getErrorAttributeOptions(request)).toMutableMap()
        val status = resolveStatus(request, body)
        if (status == HttpStatus.NO_CONTENT) {
            return ResponseEntity(status)
        }

        body["error"] = status.reasonPhrase
        body["status"] = status.value()
        return ResponseEntity(body, status)
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException::class)
    fun mediaTypeNotAcceptable(
        request: HttpServletRequest,
        error: HttpMediaTypeNotAcceptableException
    ): ResponseEntity<Map<String, Any>> {
        val body = getErrorAttributes(request, getErrorAttributeOptions(request))
        return ResponseEntity(body, resolveStatus(request, body))
    }

    protected fun getErrorAttributeOptions(request: HttpServletRequest): ErrorAttributeOptions {
        var options = ErrorAttributeOptions.defaults()
        if (errorProperties.isIncludeException) {
            options = options.including(ErrorAttributeOptions.Include.EXCEPTION)
        }
        if (isIncludeStackTrace(request)) {
            options = options.including(ErrorAttributeOptions.Include.STACK_TRACE)
        }
        if (isIncludeMessage(request)) {
            options = options.including(ErrorAttributeOptions.Include.MESSAGE)
        }
        if (isIncludeBindingErrors(request)) {
            options = options.including(ErrorAttributeOptions.Include.BINDING_ERRORS)
        }
        return options
    }

    /**
     * Determine if the stacktrace attribute should be included.
     */
    protected fun isIncludeStackTrace(request: HttpServletRequest): Boolean {
        return when (errorProperties.includeStacktrace) {
            ErrorProperties.IncludeAttribute.ALWAYS -> true
            ErrorProperties.IncludeAttribute.ON_PARAM -> getTraceParameter(request)
            else -> false
        }
    }

    /**
     * Determine if the message attribute should be included.
     */
    protected fun isIncludeMessage(request: HttpServletRequest): Boolean {
        return when (errorProperties.includeMessage) {
            ErrorProperties.IncludeAttribute.ALWAYS -> true
            ErrorProperties.IncludeAttribute.ON_PARAM -> getMessageParameter(request)
            else -> false
        }
    }

    /**
     * Determine if the errors attribute should be included.
     */
    protected fun isIncludeBindingErrors(request: HttpServletRequest): Boolean {
        return when (errorProperties.includeBindingErrors) {
            ErrorProperties.IncludeAttribute.ALWAYS -> true
            ErrorProperties.IncludeAttribute.ON_PARAM -> getErrorsParameter(request)
            else -> false
        }
    }

    protected fun resolveStatus(request: HttpServletRequest, errorAttributes: Map<String, Any>): HttpStatus {
        val code = errorAttributes.getOrDefault("code", "").toString()
        try {
            if (code.contains(".")) {
                val values = code.split(".")
                if (values.size >= 2 && values.first().isNumber()) {
                    return HttpStatus.valueOf(values[0].toInt())
                }
            }
        } catch (ignore: Exception) {
            // logger.warn("Code($code) that cannot be parsing", this)
        }
        return getStatus(request)
    }

    // companion object : KLogging()
}

fun String.isNumber() = this.all { it.isDigit() }
fun Char.isDigit() = this in '0' .. '9'
