package com.amazon.sample.ui.services.assets;

import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Mono;

public interface AssetsService <N> {
    Mono<ResponseEntity<N>> getImage(String path);
}
