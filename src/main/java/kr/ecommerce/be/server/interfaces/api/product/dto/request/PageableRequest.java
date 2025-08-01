package kr.ecommerce.be.server.interfaces.api.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record PageableRequest (

        @Schema(description = "페이지 번호", example = "1", defaultValue = "1")
        @Min(value = 1, message = "page는 1 이상이어야 합니다.")
        int page,

        @Schema(description = "페이지 크기", example = "10", defaultValue = "10")
        @Min(value = 1, message = "size는 1 이상이어야 합니다.")
        int size
) {}
