package kr.ecommerce.be.server.infrastructure.point;

import kr.ecommerce.be.server.domain.common.exception.ResourceNotFoundException;
import kr.ecommerce.be.server.domain.point.PointRepository;
import kr.ecommerce.be.server.domain.point.model.PointHistory;
import kr.ecommerce.be.server.domain.point.model.PointHistoryType;
import kr.ecommerce.be.server.domain.point.model.UserPoint;
import kr.ecommerce.be.server.infrastructure.point.dto.GetHistoryRepositoryRequestDto;
import kr.ecommerce.be.server.infrastructure.point.dto.GetPointRepositoryRequestDto;
import kr.ecommerce.be.server.infrastructure.point.dto.SavePointHistoryRepoRequestDto;
import kr.ecommerce.be.server.infrastructure.point.repository.PointHistoryJpaRepository;
import kr.ecommerce.be.server.infrastructure.point.repository.UserPointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

    private final UserPointJpaRepository userPointJpaRepository;
    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    @Override
    public UserPoint get(GetPointRepositoryRequestDto dto) {
        return userPointJpaRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void savePoint(UserPoint userPoint) {
        userPointJpaRepository.save(userPoint);
    }

    @Override
    public void saveHistory(SavePointHistoryRepoRequestDto reqRepository) {
        PointHistory history = PointHistory.builder()
                .userId(reqRepository.userId())
                .point(reqRepository.point())
                .type(reqRepository.type())
                .build();
        pointHistoryJpaRepository.save(history);
    }

    @Override
    public List<PointHistory> getHistory(GetHistoryRepositoryRequestDto repositoryRequest) {
        return pointHistoryJpaRepository.findByUserId(repositoryRequest.userId(), repositoryRequest.getPageable());
    }
}
