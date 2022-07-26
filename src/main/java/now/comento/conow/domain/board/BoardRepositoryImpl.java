package now.comento.conow.domain.board;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import now.comento.conow.web.dto.board.BoardListResponseDto;
import now.comento.conow.web.dto.board.QBoardListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static now.comento.conow.domain.board.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BoardListResponseDto> findAllPageSort(Pageable pageable) {
        JPAQuery<BoardListResponseDto> query = queryFactory
                .select(new QBoardListResponseDto(
                        board.id,
                        board.title,
                        board.content,
                        board.modifiedDate
                ))
                .from(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(board.getType(), board.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<BoardListResponseDto> results = query.fetchResults();
        List<BoardListResponseDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl(content, pageable, total);
    }
}
