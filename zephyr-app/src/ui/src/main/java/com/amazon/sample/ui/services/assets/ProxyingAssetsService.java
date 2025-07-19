package com.amazon.sample.ui.services.assets;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

public class ProxyingAssetsService implements AssetsService<byte[]> {

    @Value("${endpoints.assets}")
    private String assetsEndpoint;

    public Mono<ResponseEntity<byte[]>> getImage(String image) {
      return WebClient.builder()
            .exchangeStrategies(ExchangeStrategies.builder()
            .codecs(configurer -> configurer
                .defaultCodecs()
                .maxInMemorySize(16 * 1024 * 1024))
            .build())
                .baseUrl(this.assetsEndpoint+"/assets/"+image)
            .build().get()
                .accept(MediaType.IMAGE_JPEG)
                .retrieve()
                .bodyToMono(byte[].class)
                .map(payload -> ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
                    .body(payload));
    }
}
