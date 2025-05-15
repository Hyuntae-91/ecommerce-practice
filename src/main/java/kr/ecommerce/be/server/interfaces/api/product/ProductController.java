package kr.ecommerce.be.server.interfaces.api.product;

import jakarta.validation.Valid;
import kr.ecommerce.be.server.domain.product.dto.request.BestProductRequest;
import kr.ecommerce.be.server.domain.product.service.ProductService;
import kr.ecommerce.be.server.domain.product.dto.request.ProductListServiceRequest;
import kr.ecommerce.be.server.domain.product.dto.request.ProductServiceRequest;
import kr.ecommerce.be.server.exception.ErrorResponse;
import kr.ecommerce.be.server.interfaces.api.product.dto.request.PageableRequest;
import kr.ecommerce.be.server.interfaces.api.product.dto.response.ProductResponse;
import kr.ecommerce.be.server.interfaces.api.product.dto.request.ProductsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long productId) {
        if (productId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(400, "Missing productId"));
        }
        return ResponseEntity.ok(ProductResponse.from(
                productService.getProductById(new ProductServiceRequest(productId)))
        );
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getProducts(@Valid @ModelAttribute ProductsRequest request) {
        ProductListServiceRequest reqController = new ProductListServiceRequest(
                request.page(), request.size(), request.sort()
        );
        return ResponseEntity.ok(ProductResponse.fromList(productService.getProductList(reqController)));
    }

    @Override
    public ResponseEntity<?> getDailyBestProducts(PageableRequest request) {
        return ResponseEntity.ok(ProductResponse.fromList(
                productService.getDailyBestProducts(new BestProductRequest(request.page(), request.size()))
        ));
    }

    @Override
    public ResponseEntity<?> getWeeklyBestProducts(PageableRequest request) {
        return ResponseEntity.ok(ProductResponse.fromList(
                productService.getWeeklyBestProducts(new BestProductRequest(request.page(), request.size()))
        ));
    }

}
