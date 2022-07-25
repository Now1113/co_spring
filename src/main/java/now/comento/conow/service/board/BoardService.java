package now.comento.conow.service.board;

import now.comento.conow.web.dto.board.BoardListResponseDto;
import now.comento.conow.web.dto.board.BoardResponseDto;
import now.comento.conow.web.dto.board.BoardSaveRequestDto;
import now.comento.conow.web.dto.board.BoardUpdateRequestDto;

import java.util.List;

public interface BoardService {

    Long save(BoardSaveRequestDto dto);

    List<BoardListResponseDto> findAll();

    BoardResponseDto findById(Long id);

    void delete(Long id);

    Long update(Long id, BoardUpdateRequestDto dto);

}
