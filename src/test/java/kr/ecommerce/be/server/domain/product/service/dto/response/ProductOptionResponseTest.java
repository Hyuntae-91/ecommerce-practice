package kr.ecommerce.be.server.domain.product.service.dto.response;

import kr.ecommerce.be.server.domain.order.model.OrderOption;
import kr.ecommerce.be.server.domain.product.dto.response.ProductOptionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("ProductOptionResponse 유효성 검증")
class ProductOptionResponseTest {

    @Test
    @DisplayName("성공: 정상 값으로 생성")
    void validInput_shouldSucceed() {
        assertThatCode(() -> new ProductOptionResponse(1L, 250, 10))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패: optionId가 null일 경우 예외 발생")
    void nullOptionId_shouldThrow() {
        assertThatThrownBy(() -> new ProductOptionResponse(null, 250, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("optionId는 1 이상이어야 합니다.");
    }

    @Test
    @DisplayName("실패: optionId가 0일 경우 예외 발생")
    void invalidOptionId_shouldThrow() {
        assertThatThrownBy(() -> new ProductOptionResponse(0L, 250, 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: size가 음수일 경우 예외 발생")
    void negativeSize_shouldThrow() {
        assertThatThrownBy(() -> new ProductOptionResponse(1L, -1, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("size는 0 이상이어야 합니다.");
    }

    @Test
    @DisplayName("실패: stock이 음수일 경우 예외 발생")
    void negativeStock_shouldThrow() {
        assertThatThrownBy(() -> new ProductOptionResponse(1L, 250, -5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("stock은 0 이상이어야 합니다.");
    }

    @Test
    @DisplayName("성공: OrderOption으로부터 from() 변환 정상 작동")
    void fromOrderOption_shouldConvertCorrectly() {
        OrderOption option = OrderOption.builder()
                .id(100L)
                .size(275)
                .stockQuantity(12)
                .build();

        ProductOptionResponse response = ProductOptionResponse.from(option);

        assertThat(response.optionId()).isEqualTo(100L);
        assertThat(response.size()).isEqualTo(275);
        assertThat(response.stock()).isEqualTo(12);
    }
}
