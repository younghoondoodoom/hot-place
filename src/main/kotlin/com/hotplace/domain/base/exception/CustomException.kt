package com.hotplace.domain.base.exception

open class ServiceException(override val message: String) : RuntimeException()

data class HttpClientException(override val message: String) : ServiceException(message = message)
