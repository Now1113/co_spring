package now.comento.conow.web.dto.board;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import now.comento.conow.domain.board.Board;

@Getter
@Setter
public class BoardListResponseDto {

    private Long id;

    private String title;

    private String content;

    private String modifiedDate;

    public BoardListResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.modifiedDate = board.getModifiedDate();
    }

    @QueryProjection
    public BoardListResponseDto(Long id, String title, String content, String modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
    }
}
