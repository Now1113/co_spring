package now.comento.conow.service;

import now.comento.conow.domain.Board;
import now.comento.conow.web.dto.BoardDto;

import java.util.List;

public interface BoardService {

    Long save(BoardDto dto);

    List<BoardDto> findAll();

    default BoardDto entityToDto(Board board) {
        BoardDto dto = BoardDto.builder()
                .content(board.getContent())
                .title(board.getTitle())
                .build();

        return dto;
    }
}
