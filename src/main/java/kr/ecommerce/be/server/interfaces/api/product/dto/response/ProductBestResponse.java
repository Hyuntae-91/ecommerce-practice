package kr.ecommerce.be.server.interfaces.api.product.dto.response;

import kr.ecommerce.be.server.domain.product.model.Product;

import java.util.List;

public record ProductBestResponse(
        List<ProductDto> products
) {
    public static ProductBestResponse from(List<Product> products) {
        List<ProductDto> productResponses = products.stream()
                .map(ProductDto::from)
                .toList();
        return new ProductBestResponse(productResponses);
    }
}