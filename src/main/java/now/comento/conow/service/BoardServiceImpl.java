package now.comento.conow.service;

import lombok.RequiredArgsConstructor;
import now.comento.conow.domain.Board;
import now.comento.conow.domain.BoardRepository;
import now.comento.conow.web.dto.BoardDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Long save(BoardDto dto) {
        return boardRepository.save(dto.toEntity()).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardDto> findAll() {
        List<Board> list = boardRepository.findAll();




        return null;
    }
}
