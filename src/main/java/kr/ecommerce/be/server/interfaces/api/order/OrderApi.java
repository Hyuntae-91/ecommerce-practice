package kr.ecommerce.be.server.interfaces.api.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ecommerce.be.server.exception.ErrorResponse;
import kr.ecommerce.be.server.interfaces.api.order.dto.request.AddCartRequest;
import kr.ecommerce.be.server.interfaces.api.order.dto.response.GetCartItemsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/order")
@Tag(name = "Order", description = "주문 관련 API")
public interface OrderApi {

    @Operation(summary = "장바구니 조회", description = "장바구니에 담긴 상품 목록을 조회하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "장바구니 조회 성공",
                    content = @Content(schema = @Schema(implementation = GetCartItemsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "장바구니 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/cart")
    ResponseEntity<?> getCart(
            @RequestHeader("userId") Long userId
    );

    @Operation(summary = "장바구니 추가", description = "상품을 장바구니에 담는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "장바구니 추가 성공"),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/cart")
    ResponseEntity<?> addToCart(
            @RequestHeader("userId") Long userId,
            @RequestBody AddCartRequest request
    );
}

