package now.comento.conow.service;

import lombok.RequiredArgsConstructor;
import now.comento.conow.domain.Board;
import now.comento.conow.domain.BoardRepository;
import now.comento.conow.web.dto.BoardDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return boardRepository.findAll().stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public BoardDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new BoardDto(board);
    }

    @Override
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        boardRepository.delete(board);
    }

}
