package com.amazon.sample.ui.services.assets;

import org.springframework.http.ResponseEntity;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

public class MockAssetsService implements AssetsService<ClassPathResource> {

    public Mono<ResponseEntity<ClassPathResource>> getImage(String image) {
      ClassPathResource classPathResource = new ClassPathResource("static/assets/img/sample_product.jpg");
      
      return Mono.just(new ResponseEntity<ClassPathResource>(classPathResource, HttpStatus.OK));
    }
}
