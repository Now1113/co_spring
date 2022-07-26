package now.comento.conow.domain.board;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static now.comento.conow.domain.board.QBoard.board;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Board> findAllPageSort(Pageable pageable) {
        JPAQuery<Board> query = queryFactory
                .selectFrom(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return null;
    }
}
