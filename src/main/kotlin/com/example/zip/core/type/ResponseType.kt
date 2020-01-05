package com.example.zip.core.type

import com.example.zip.`interface`.response.AbcResponse
import com.example.zip.`interface`.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class ResponseType (
    open val resultCode: String, open val httpStatus: HttpStatus, open var description: String? = null
) {
    sealed class Success(override val resultCode: String, override val httpStatus: HttpStatus)
        : ResponseType(resultCode, httpStatus, null) {
        object OK: Success("ZIP2001", HttpStatus.OK)
        object CREATED: Success("ZIP2002", HttpStatus.CREATED)
        object ACCEPTED: Success("ZIP2003", HttpStatus.ACCEPTED)
        object MODIFIED: Success("ZIP2004", HttpStatus.CREATED)
        object PARTIAL_SUCCESS: Success("ZIP2005", HttpStatus.MULTI_STATUS)
    }
    sealed class Error(override val resultCode: String, override val httpStatus: HttpStatus, override var description: String?)
        : ResponseType(resultCode, httpStatus, description) {
        object MANDATORY_HEADER_MISSING: Error("ZIP4001", HttpStatus.BAD_REQUEST, "Missing Header - ")
        object MANDATORY_PARAM_MISSING: Error("ZIP4002", HttpStatus.BAD_REQUEST, "Missing Param - ")
        object INVALID_DATA: Error("ZIP4003", HttpStatus.BAD_REQUEST, "Invalid Parameter - ")
        object NOT_EXISTING_RESOURCE: Error("ZIP4004", HttpStatus.NOT_FOUND, "Could not read document - ")
        object NOT_ACCEPTABLE: Error("ZIP4005", HttpStatus.BAD_REQUEST, "Not acceptable - ")
        object DATABASE_TRANSACTION_ERROR: Error("ZIP4006", HttpStatus.BAD_REQUEST, "DB data processing error - ")
        object DATABASE_UNAVILABLE: Error("ZIP5001", HttpStatus.SERVICE_UNAVAILABLE, "DB Error - ")
        object SYSTEM_CONFIGURATION_ERROR: Error("ZIP5002", HttpStatus.INTERNAL_SERVER_ERROR, "Configuration unavailable - ")
        object INTERNAL_SERVER_ERROR: Error("ZIP5999", HttpStatus.INTERNAL_SERVER_ERROR, "ZIP Server Error - ")
        public inline fun sendError(exceptionMessage: String)
            = ExceptionResponse(ErrorResponse(this, exceptionMessage), this.httpStatus)
    }
}
typealias ExceptionResponse = ResponseEntity<ErrorResponse>
typealias WebResponse = ResponseEntity<AbcResponse>