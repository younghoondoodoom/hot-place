package com.hotplace.infra.client.seoulCommonDataClient

import com.hotplace.infra.client.seoulCommonDataClient.config.SeoulCommonDataClientConfig
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class SeoulCommonDataApiClient(
    @Qualifier(SeoulCommonDataClientConfig.SEOUL_COMMON_DATA_WEBCLIENT) private val webClient: WebClient,
) {

}