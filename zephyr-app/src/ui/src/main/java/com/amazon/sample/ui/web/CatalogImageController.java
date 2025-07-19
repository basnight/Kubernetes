package com.amazon.sample.ui.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.amazon.sample.ui.services.assets.AssetsService;

import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * This controller serves product images from the catalog backend, adding cache control headers along the way
 */
@RestController
public class CatalogImageController {

  private AssetsService assetsService;

  public CatalogImageController(@Autowired AssetsService assetsService) {
      this.assetsService = assetsService;
  }

    @GetMapping(value = "/assets/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Mono<ResponseEntity<byte[]>> catalogueImage(@PathVariable String image) {
        return this.assetsService.getImage(image);
    }
}
