package now.comento.conow.web.dto.board;

import lombok.Getter;
import now.comento.conow.domain.board.Board;

@Getter
public class BoardResponseDto {

    private Long id;

    private String title;

    private String content;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
