package now.comento.conow.web.dto;

import lombok.*;
import now.comento.conow.domain.Board;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;

    private String title;

    private String content;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();

        return board;
    }

    @Builder
    public BoardDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


}
