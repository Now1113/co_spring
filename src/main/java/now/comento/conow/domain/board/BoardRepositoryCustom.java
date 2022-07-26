package now.comento.conow.domain.board;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findAllPageSort(Pageable pageable);

}
