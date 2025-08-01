package kr.ecommerce.be.server.interfaces.api.order;

import jakarta.validation.Valid;
import kr.ecommerce.be.server.application.order.OrderFacade;
import kr.ecommerce.be.server.application.order.dto.AddCartFacadeRequest;
import kr.ecommerce.be.server.domain.order.dto.response.AddCartServiceResponse;
import kr.ecommerce.be.server.domain.order.dto.response.CartItemServiceResponse;
import kr.ecommerce.be.server.domain.order.dto.request.GetCartServiceRequest;
import kr.ecommerce.be.server.interfaces.api.order.dto.request.AddCartRequest;
import kr.ecommerce.be.server.domain.order.service.OrderService;
import kr.ecommerce.be.server.exception.ErrorResponse;
import kr.ecommerce.be.server.interfaces.api.order.dto.response.AddCartResponse;
import kr.ecommerce.be.server.interfaces.api.order.dto.response.CartItem;
import kr.ecommerce.be.server.interfaces.api.order.dto.response.GetCartItemsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;
    private final OrderFacade orderFacade;

    @Override
    public ResponseEntity<?> getCart(
            @RequestHeader("userId") Long userId
    ) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(400, "Missing userId header"));
        }
        GetCartServiceRequest getCartServiceRequest = new GetCartServiceRequest(userId);
        CartItemServiceResponse result = orderService.getCart(getCartServiceRequest);
        List<CartItem> cartList = result.cartList().stream()
                .map(CartItem::from)
                .toList();
        return ResponseEntity.ok(new GetCartItemsResponse(cartList, result.totalPrice()));
    }

    @Override
    public ResponseEntity<?> addToCart(
            @RequestHeader("userId") Long userId,
            @RequestBody @Valid AddCartRequest request
    ) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(400, "Missing userId header"));
        }
        AddCartFacadeRequest addCartServiceRequest = new AddCartFacadeRequest(
                userId, request.productId(), request.optionId(), request.quantity()
        );
        AddCartServiceResponse result = orderFacade.addCart(addCartServiceRequest);
        List<CartItem> cartList = result.cartList().stream()
                .map(CartItem::from)
                .toList();

        return ResponseEntity.ok(new AddCartResponse(cartList, result.totalPrice()));
    }
}
