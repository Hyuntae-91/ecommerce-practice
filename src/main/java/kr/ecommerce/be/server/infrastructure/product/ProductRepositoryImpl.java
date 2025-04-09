package kr.ecommerce.be.server.infrastructure.product;

import kr.ecommerce.be.server.domain.common.exception.ResourceNotFoundException;
import kr.ecommerce.be.server.domain.product.ProductRepository;
import kr.ecommerce.be.server.domain.product.model.Product;
import kr.ecommerce.be.server.infrastructure.product.dto.GetProductsRepositoryRequestDto;
import kr.ecommerce.be.server.infrastructure.product.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product findById(Long id) {
        return productJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
    }

    @Override
    public List<Product> findAll(GetProductsRepositoryRequestDto reqRepository) {
        return productJpaRepository.findAll(reqRepository.getPageable()).getContent();
    }

    @Override
    public List<Product> findPopularTop5() {
        //return productJpaRepository.findTop5ByOrderByScoreDesc(); // 가정: score 필드가 있음
        return null;
    }

    @Override
    public void recalculateBestProducts() {
        // TODO: 추후 통계 계산 쿼리로 대체
    }
}
