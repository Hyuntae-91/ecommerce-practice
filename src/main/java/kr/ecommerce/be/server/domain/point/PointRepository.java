package kr.ecommerce.be.server.domain.point;

import kr.ecommerce.be.server.domain.point.model.PointHistory;
import kr.ecommerce.be.server.domain.point.model.UserPoint;
import kr.ecommerce.be.server.infrastructure.point.dto.GetHistoryRepositoryRequestDto;
import kr.ecommerce.be.server.infrastructure.point.dto.GetPointRepositoryRequestDto;
import kr.ecommerce.be.server.infrastructure.point.dto.SavePointHistoryRepoRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PointRepository {
    UserPoint get(GetPointRepositoryRequestDto getPointRepositoryRequestDto);
    void savePoint(UserPoint userPoint);
    void saveHistory(SavePointHistoryRepoRequestDto reqRepository);
    List<PointHistory> getHistory(GetHistoryRepositoryRequestDto repositoryRequest);
}
