// UserResponse.java
package kr.ecommerce.be.server.dto.point;

import java.util.List;

public record PointResponse(
        Long userId,
        Long point
) {}
