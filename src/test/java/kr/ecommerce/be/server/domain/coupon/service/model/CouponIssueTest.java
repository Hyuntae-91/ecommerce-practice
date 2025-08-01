package kr.ecommerce.be.server.domain.coupon.service.model;

import kr.ecommerce.be.server.domain.coupon.dto.response.CouponIssueDto;
import kr.ecommerce.be.server.domain.coupon.model.Coupon;
import kr.ecommerce.be.server.domain.coupon.model.CouponIssue;
import kr.ecommerce.be.server.domain.coupon.model.CouponType;
import kr.ecommerce.be.server.exception.custom.InvalidCouponUseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CouponIssueTest {

    private String nowPlus(int days) {
        return java.time.LocalDateTime.now().plusDays(days).toString();
    }

    @Test
    @DisplayName("성공: FIXED 쿠폰의 할인 계산")
    void calculateFinalPrice_fixed() {
        Coupon coupon = new Coupon(1L, CouponType.FIXED, "desc", 1000, 10, 0, 7, "2024-04-11T00:00:00", "2024-04-11T00:00:00");
        CouponIssue issue = CouponIssue.of(
                1L,
                1L,
                coupon.getId(),
                0,
                nowPlus(-7),
                nowPlus(7),
                nowPlus(0),
                nowPlus(0)
        );
        long result = issue.calculateFinalPrice(5000L, coupon);
        assertThat(result).isEqualTo(4000L);
    }

    @Test
    @DisplayName("성공: PERCENT 쿠폰의 할인 계산")
    void calculateFinalPrice_percent() {
        Coupon coupon = new Coupon(2L, CouponType.PERCENT, "10% 할인", 10, 10, 0, 7, "2024-04-11T00:00:00", "2024-04-11T00:00:00");
        CouponIssue issue = CouponIssue.of(
                1L,
                1L,
                coupon.getId(),
                0,
                nowPlus(-7),
                nowPlus(7),
                nowPlus(0),
                nowPlus(0)
        );
        long result = issue.calculateFinalPrice(5000L, coupon);
        assertThat(result).isEqualTo(4500L);
    }

    @Test
    @DisplayName("성공: 할인액이 원금보다 클 경우 최대 할인은 원금까지")
    void calculateFinalPrice_cannot_exceed_original_price() {
        Coupon coupon = new Coupon(3L, CouponType.FIXED, "Big 할인", 99999, 10, 0, 7, "2024-04-11T00:00:00", "2024-04-11T00:00:00");
        CouponIssue issue = CouponIssue.of(
                1L,
                1L,
                coupon.getId(),
                0,
                nowPlus(-7),
                nowPlus(7),
                nowPlus(0),
                nowPlus(0)
        );
        long result = issue.calculateFinalPrice(3000L, coupon);
        assertThat(result).isEqualTo(0L);
    }

    @Test
    @DisplayName("성공: 사용 가능 상태 검증 통과")
    void validateUsable_success() {
        Coupon coupon = new Coupon(1L, CouponType.FIXED, "desc", 1000, 10, 0, 7, "2024-04-11T00:00:00", "2024-04-11T00:00:00");
        CouponIssue issue = CouponIssue.of(
                1L,
                1L,
                coupon.getId(),
                0,
                nowPlus(-7),
                nowPlus(7),
                nowPlus(0),
                nowPlus(0)
        );
        assertThatCode(issue::validateUsable).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패: 쿠폰 상태가 0이 아닌 경우 예외 발생")
    void validateUsable_invalid_state() {
        Coupon coupon = new Coupon(1L, CouponType.FIXED, "desc", 1000, 10, 0, 7, "2024-04-11T00:00:00", "2024-04-11T00:00:00");
        CouponIssue issue = CouponIssue.of(
                1L,
                1L,
                coupon.getId(),
                1,
                nowPlus(-7),
                nowPlus(7),
                nowPlus(0),
                nowPlus(0)
        );
        assertThatThrownBy(issue::validateUsable)
                .isInstanceOf(InvalidCouponUseException.class)
                .hasMessageContaining("사용할 수 없는 쿠폰");
    }

    @Test
    @DisplayName("성공: 쿠폰 사용 처리")
    void markUsed_success() {
        Coupon coupon = new Coupon(1L, CouponType.FIXED, "desc", 1000, 10, 0, 7, "2024-04-11T00:00:00", "2024-04-11T00:00:00");
        CouponIssue issue = CouponIssue.of(
                1L,
                1L,
                coupon.getId(),
                0,
                nowPlus(-7),
                nowPlus(7),
                nowPlus(0),
                nowPlus(0)
        );
        issue.markUsed();
        assertThat(issue.getState()).isEqualTo(1);
    }

    @Test
    @DisplayName("성공: 쿠폰 상태 업데이트")
    void updateState_success() {
        Coupon coupon = new Coupon(1L, CouponType.FIXED, "desc", 1000, 10, 0, 7, "2024-04-11T00:00:00", "2024-04-11T00:00:00");
        CouponIssue issue = CouponIssue.of(
                1L,
                1L,
                coupon.getId(),
                0,
                nowPlus(-7),
                nowPlus(7),
                nowPlus(0),
                nowPlus(0)
        );
        issue.updateState(-1);
        assertThat(issue.getState()).isEqualTo(-1);
    }

    @Test
    @DisplayName("성공: toDto 동작 확인")
    void toDto_success() {
        Coupon coupon = new Coupon(1L, CouponType.FIXED, "desc", 1000, 10, 0, 7, "2024-04-11T00:00:00", "2024-04-11T00:00:00");
        CouponIssue issue = CouponIssue.of(
                1L,
                1L,
                coupon.getId(),
                0,
                nowPlus(-7),
                nowPlus(7),
                nowPlus(0),
                nowPlus(0)
        );
        CouponIssueDto dto = issue.toDto(coupon);
        assertThat(dto).isNotNull();
        assertThat(dto.couponId()).isEqualTo(1L);
        assertThat(dto.discount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("실패: 쿠폰 사용 기간이 아님")
    void validateUsable_invalid_period() {
        Coupon coupon = new Coupon(1L, CouponType.FIXED, "desc", 1000, 10, 0, 7,
                "2024-04-11T00:00:00", "2024-04-11T00:00:00");

        // 쿠폰 사용 가능 기간이 현재보다 미래로 설정됨
        CouponIssue issue = CouponIssue.of(
                1L,
                1L,
                coupon.getId(),
                0,
                nowPlus(1),  // 시작일: 내일
                nowPlus(10), // 종료일: 10일 뒤
                nowPlus(0),
                nowPlus(0)
        );

        assertThatThrownBy(issue::validateUsable)
                .isInstanceOf(InvalidCouponUseException.class)
                .hasMessageContaining("쿠폰 사용 가능 기간이 아닙니다.");
    }

}
