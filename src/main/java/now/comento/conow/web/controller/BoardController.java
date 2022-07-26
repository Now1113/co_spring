package now.comento.conow.web.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import now.comento.conow.domain.board.Board;
import now.comento.conow.domain.board.BoardRepositoryImpl;
import now.comento.conow.web.dto.page.PageDto;
import now.comento.conow.service.board.BoardServiceImpl;
import now.comento.conow.web.dto.board.BoardListResponseDto;
import now.comento.conow.web.dto.board.BoardResponseDto;
import now.comento.conow.web.dto.board.BoardSaveRequestDto;
import now.comento.conow.web.dto.board.BoardUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardServiceImpl boardService;
    private final BoardRepositoryImpl boardRepository;

    //전체보기
    @GetMapping
    public String findAll(Model model, @PageableDefault(size = 5, sort="id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<BoardListResponseDto> boardList = boardRepository.findAllPageSort(pageable);
        model.addAttribute("boardList", boardList);
        model.addAttribute("page", new PageDto(boardList.getTotalElements(), pageable));
        return "/boards/list";
    }

    //한개만 보기
    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id, Model model) {
        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("boards", dto);
        return "/boards/board";
    }

    //등록폼
    @GetMapping("/post")
    public String postForm(Model model) {
        model.addAttribute("dto", Board.builder().build());
        return "/boards/post";
    }

    //등록
    @PostMapping("/post")
    public String post(@Validated @ModelAttribute("dto") BoardSaveRequestDto dto,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "/boards/post";
        }

        boardService.save(dto);
        return "redirect:/boards";
    }

    //삭제
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/boards";
    }

    //업데이트폼
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("dto", dto);
        return "/boards/edit";
    }

    //업데이트
    @PutMapping("/edit/{id}")
    public String editBoard(@PathVariable Long id, @Validated @ModelAttribute("dto") BoardUpdateRequestDto dto,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            if (dto.getTitle() == null) {
                bindingResult.addError(new FieldError("dto", "title","제목은 공백X"));
            }
            return "redirect:/boards/edit/" + id;
        }

        boardService.update(id, dto);
        return "redirect:/boards/" + id;
    }
}
