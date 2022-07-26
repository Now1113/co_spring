package now.comento.conow.domain.board;

import now.comento.conow.web.dto.board.BoardListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepositoryCustom {

    Page<BoardListResponseDto> findAllPageSort(Pageable pageable);

}
