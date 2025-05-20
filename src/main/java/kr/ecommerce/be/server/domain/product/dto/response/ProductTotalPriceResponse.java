package kr.ecommerce.be.server.domain.product.dto.response;

public record ProductTotalPriceResponse(long totalPrice) {

    public static ProductTotalPriceResponse from(long totalPrice) {
        return new ProductTotalPriceResponse(totalPrice);
    }
}
