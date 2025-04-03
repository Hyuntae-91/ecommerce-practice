package kr.ecommerce.be.server.dto.product;

import java.util.List;

public record ProductBestResponse(
        List<ProductResponse> products
) {}
