package kr.ecommerce.be.server.application.order.dto;

public record AddCartFacadeRequest (
        Long userId,
        Long productId,
        Long optionId,
        Integer quantity
) {}
