package now.comento.conow.web.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateRequestDto {


    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    private String content;

    @Builder
    public BoardUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
