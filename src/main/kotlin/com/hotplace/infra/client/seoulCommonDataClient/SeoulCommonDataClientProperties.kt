package com.hotplace.infra.client.seoulCommonDataClient

import com.hotplace.infra.client.core.HttpClientFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties(prefix = "seoul-common-data")
data class SeoulCommonDataClientProperties(
    override val connectionTimeout: Duration,
    override val readTimeout: Duration,
    override val writeTimeout: Duration,
    override val baseUrl: String,
    val key: String,
) : HttpClientFactory.ClientProperties(connectionTimeout, readTimeout, writeTimeout, baseUrl)
