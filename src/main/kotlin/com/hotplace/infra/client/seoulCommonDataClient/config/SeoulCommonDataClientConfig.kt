package com.hotplace.infra.client.seoulCommonDataClient.config

import com.hotplace.infra.client.core.HttpClientFactory
import com.hotplace.infra.client.seoulCommonDataClient.SeoulCommonDataClientProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@EnableConfigurationProperties(value = [SeoulCommonDataClientProperties::class])
class SeoulCommonDataClientConfig {

    @Bean(SEOUL_COMMON_DATA_WEBCLIENT)
    fun webClient(appPushClientProperties: SeoulCommonDataClientProperties): WebClient {
        return HttpClientFactory.INSTANCE
            .builder(appPushClientProperties)
            .defaultUriVariables(mapOf(Pair("key", appPushClientProperties.key)))
            .build()
    }

    companion object {
        const val SEOUL_COMMON_DATA_WEBCLIENT = "SEOUL_COMMON_DATA_WEBCLIENT"
    }
}
