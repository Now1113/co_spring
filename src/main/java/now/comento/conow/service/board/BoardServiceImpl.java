package now.comento.conow.service.board;

import lombok.RequiredArgsConstructor;
import now.comento.conow.domain.board.Board;
import now.comento.conow.domain.board.BoardRepository;
import now.comento.conow.web.dto.board.BoardListResponseDto;
import now.comento.conow.web.dto.board.BoardResponseDto;
import now.comento.conow.web.dto.board.BoardSaveRequestDto;
import now.comento.conow.web.dto.board.BoardUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long save(BoardSaveRequestDto dto) {
        return boardRepository.save(dto.toEntity()).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new BoardResponseDto(board);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        boardRepository.delete(board);
    }

    @Override
    @Transactional
    public Long update(Long id, BoardUpdateRequestDto dto) {
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        board.update(dto.getTitle(), dto.getContent());

        return id;
    }

}
