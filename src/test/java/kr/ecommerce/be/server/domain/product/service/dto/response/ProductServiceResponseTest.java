package kr.ecommerce.be.server.domain.product.service.dto.response;

import kr.ecommerce.be.server.domain.product.dto.response.ProductOptionResponse;
import kr.ecommerce.be.server.domain.product.dto.response.ProductServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("ProductServiceResponse 유효성 검증")
class ProductServiceResponseTest {

    private static final List<ProductOptionResponse> VALID_OPTIONS = List.of(
            new ProductOptionResponse(1L, 250, 10)
    );

    @Test
    @DisplayName("성공: 유효한 데이터로 생성")
    void validInput_doesNotThrow() {
        assertThatCode(() -> new ProductServiceResponse(1L, "상품", 1000L, 1, "2025-04-01", VALID_OPTIONS))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패: id가 null이면 예외 발생")
    void nullId_throwsException() {
        assertThatThrownBy(() -> new ProductServiceResponse(null, "상품", 1000L, 1, "2025-04-01", VALID_OPTIONS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: name이 공백이면 예외 발생")
    void blankName_throwsException() {
        assertThatThrownBy(() -> new ProductServiceResponse(1L, "  ", 1000L, 1, "2025-04-01", VALID_OPTIONS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: price가 null이면 예외 발생")
    void nullPrice_throwsException() {
        assertThatThrownBy(() -> new ProductServiceResponse(1L, "상품", null, 1, "2025-04-01", VALID_OPTIONS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: price가 음수이면 예외 발생")
    void negativePrice_throwsException() {
        assertThatThrownBy(() -> new ProductServiceResponse(1L, "상품", -100L, 1, "2025-04-01", VALID_OPTIONS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: createdAt이 공백이면 예외 발생")
    void blankCreatedAt_throwsException() {
        assertThatThrownBy(() -> new ProductServiceResponse(1L, "상품", 1000L, 1, " ", VALID_OPTIONS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: createdAt이 null이면 예외 발생")
    void nullCreatedAt_throwsException() {
        assertThatThrownBy(() -> new ProductServiceResponse(1L, "상품", 1000L, 1, null, VALID_OPTIONS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("성공: withOptions로 새로운 옵션 리스트 적용")
    void withOptions_setsOptionsCorrectly() {
        ProductServiceResponse base = new ProductServiceResponse(1L, "상품", 1000L, 1, "2025-04-01", List.of());
        List<ProductOptionResponse> newOptions = List.of(
                new ProductOptionResponse(2L, 260, 5)
        );

        ProductServiceResponse updated = base.withOptions(newOptions);
        assertThat(updated.options()).hasSize(1);
        assertThat(updated.options().get(0).optionId()).isEqualTo(2L);
    }


}
