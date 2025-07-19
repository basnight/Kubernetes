package com.amazon.sample.ui.config;

import com.amazon.sample.ui.clients.carts.api.CartsApi;
import com.amazon.sample.ui.clients.carts.api.ItemsApi;
import com.amazon.sample.ui.clients.catalog.api.CatalogApi;
import com.amazon.sample.ui.clients.checkout.api.CheckoutApi;
import com.amazon.sample.ui.services.assets.AssetsService;
import com.amazon.sample.ui.services.assets.MockAssetsService;
import com.amazon.sample.ui.services.assets.ProxyingAssetsService;
import com.amazon.sample.ui.services.carts.CartsService;
import com.amazon.sample.ui.services.carts.MockCartsService;
import com.amazon.sample.ui.services.carts.WebClientCartsService;
import com.amazon.sample.ui.services.catalog.CatalogService;
import com.amazon.sample.ui.services.catalog.MockCatalogService;
import com.amazon.sample.ui.services.catalog.WebClientCatalogService;
import com.amazon.sample.ui.services.catalog.model.CatalogMapper;
import com.amazon.sample.ui.services.checkout.CheckoutService;
import com.amazon.sample.ui.services.checkout.MockCheckoutService;
import com.amazon.sample.ui.services.checkout.WebClientCheckoutService;
import com.amazon.sample.ui.services.checkout.model.CheckoutMapper;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreServices {

    @Bean
    @ConditionalOnProperty(prefix = "endpoints", name = "catalog")
    public CatalogService catalogService(CatalogApi catalogApi, CatalogMapper mapper) {
        return new WebClientCatalogService(catalogApi, mapper);
    }

    @Bean
    @ConditionalOnProperty(prefix = "endpoints", name = "catalog", havingValue = "false", matchIfMissing = true)
    public CatalogService mockCatalogService() {
        return new MockCatalogService();
    }

    @Bean
    @ConditionalOnProperty(prefix = "endpoints", name = "carts")
    public CartsService cartsService(CartsApi cartsApi, ItemsApi itemsApi, CatalogService catalogService) {
        return new WebClientCartsService(cartsApi, itemsApi, catalogService);
    }


    @Bean
    @ConditionalOnProperty(prefix = "endpoints", name = "carts", havingValue = "false", matchIfMissing = true)
    public CartsService mockCartsService(CatalogService catalogService) {
        return new MockCartsService(catalogService);
    }

    @Bean
    @ConditionalOnProperty(prefix = "endpoints", name = "checkout")
    public CheckoutService checkoutService(CheckoutApi api, CheckoutMapper mapper, CartsService cartsService) {
        return new WebClientCheckoutService(api, mapper, cartsService);
    }

    @Bean
    @ConditionalOnProperty(prefix = "endpoints", name = "checkout", havingValue = "false", matchIfMissing = true)
    public CheckoutService mockCheckoutService(CheckoutMapper mapper, CartsService cartsService) {
        return new MockCheckoutService(mapper, cartsService);
    }

    @Bean
    @ConditionalOnProperty(prefix = "endpoints", name = "assets")
    public AssetsService<?> assetsService() {
        return new ProxyingAssetsService();
    }

    @Bean
    @ConditionalOnProperty(prefix = "endpoints", name = "assets", havingValue = "false", matchIfMissing = true)
    public AssetsService<?> mockAssetsService() {
        return new MockAssetsService();
    }
}
