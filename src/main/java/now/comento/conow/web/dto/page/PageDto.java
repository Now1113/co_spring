package now.comento.conow.web.dto.page;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
public class PageDto {

    //몇 개의 페이지로 구성할건지
    private final int PAGENUM = 5;
    //페이지당 몇 개 표시할건지
    private int pageSize;
    private int startPage;
    private int endPage;
    private int curPage;
    private boolean prev, next;

    private long total;

    public PageDto(long total, Pageable pageable) {
        this.total = total;
        this.curPage = pageable.getPageNumber();
        this.pageSize = pageable.getPageSize();

        this.endPage = (int) (Math.ceil((curPage+1) / 10)) * 10;
        this.startPage = this.endPage - (PAGENUM - 1);

        int realEnd = (int) (Math.ceil((total * 1.0) / pageSize));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = (curPage + 1) > 1;
        this.next = (curPage + 1) < realEnd;
    }

}
