package com.example.zip.core.exception

import com.example.zip.core.type.ResponseType

class ServiceException(val errors: ResponseType.Error, override val message: String) : RuntimeException(message)