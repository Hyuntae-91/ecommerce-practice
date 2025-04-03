package kr.ecommerce.be.server.dto.product;

public record ProductResponse(
        Long id,
        String name,
        Long price,
        Long state,
        String createdAt
) {}