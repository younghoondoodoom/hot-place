package com.hotplace.infra.client.core

import com.hotplace.domain.base.exception.HttpClientException
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

enum class HttpClientFactory {
    INSTANCE,
    ;

    abstract class ClientProperties(
        open val connectionTimeout: Duration,
        open val readTimeout: Duration,
        open val writeTimeout: Duration,
        open val baseUrl: String,
    )

    fun builder(properties: ClientProperties): WebClient.Builder {
        return createDefaultWebClientBuilder(properties)
    }

    fun build(properties: ClientProperties): WebClient {
        return createDefaultWebClientBuilder(properties).build()
    }

    private fun createHttpClient(properties: ClientProperties): HttpClient {
        return HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.connectionTimeout.toMillis().toInt())
            .doOnConnected {
                it.addHandlerLast(ReadTimeoutHandler(properties.readTimeout.seconds.toInt()))
                it.addHandlerLast(WriteTimeoutHandler(properties.writeTimeout.seconds.toInt()))
            }
            .doOnRequestError { request, throwable ->
                throw HttpClientException("${request.method()} ${request.uri()} failed. ${throwable.message}")
            }
    }

    private fun createDefaultWebClientBuilder(properties: ClientProperties): WebClient.Builder {
        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(createHttpClient(properties)))
            .codecs {
                it.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)
            }
            .baseUrl(properties.baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
    }
}
