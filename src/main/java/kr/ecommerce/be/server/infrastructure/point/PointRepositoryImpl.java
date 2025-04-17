package kr.ecommerce.be.server.infrastructure.point;

import kr.ecommerce.be.server.exception.custom.ResourceNotFoundException;
import kr.ecommerce.be.server.domain.point.repository.PointRepository;
import kr.ecommerce.be.server.domain.point.model.UserPoint;
import kr.ecommerce.be.server.infrastructure.point.repository.UserPointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

    private final UserPointJpaRepository userPointJpaRepository;

    @Override
    public UserPoint get(Long userId) {
        return userPointJpaRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void savePoint(UserPoint userPoint) { userPointJpaRepository.save(userPoint); }

}
