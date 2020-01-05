package com.example.zip.core.exception

import com.example.zip.core.type.ResponseType
import com.example.zip.util.LoggerUtil
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataAccessException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.sql.SQLException
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ServiceException::class)
    fun serviceExceptionHandle(exception: ServiceException, request: HttpServletRequest)
        = loggingAndThrow(exception) { exception.errors.sendError(exception.message) }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun parameterExceptionHandle(exception: MissingServletRequestParameterException, request: HttpServletRequest)
        = loggingAndThrow(exception) { ResponseType.Error.MANDATORY_PARAM_MISSING.sendError(exception.parameterName) }

    @ExceptionHandler(SQLException::class)
    fun dbExceptionHandle(exception: SQLException, request: HttpServletRequest)
        = loggingAndThrow(exception) { ResponseType.Error.DATABASE_TRANSACTION_ERROR.sendError(exception.message!!) }

    @ExceptionHandler(DataAccessException::class)
    fun dbDataAccessExceptionHandle(exception: DataAccessException, request: HttpServletRequest)
        = loggingAndThrow(exception) { ResponseType.Error.DATABASE_TRANSACTION_ERROR.sendError(
        (exception.cause as? ConstraintViolationException)?.sqlException?.message
            ?: exception.localizedMessage) }

    @ExceptionHandler(RuntimeException::class)
    fun runtimeExceptionHandle(exception: RuntimeException, request: HttpServletRequest)
        = loggingAndThrow(exception) { ResponseType.Error.INTERNAL_SERVER_ERROR.sendError(exception.localizedMessage) }
}

inline fun <R> loggingAndThrow(exception: Exception, block: () -> R): R {
    LoggerUtil.errorLog(exception)
    return block()
}