package now.comento.conow.web.controller;


import lombok.RequiredArgsConstructor;
import now.comento.conow.domain.board.Board;
import now.comento.conow.service.board.BoardServiceImpl;
import now.comento.conow.web.dto.board.BoardListResponseDto;
import now.comento.conow.web.dto.board.BoardResponseDto;
import now.comento.conow.web.dto.board.BoardSaveRequestDto;
import now.comento.conow.web.dto.board.BoardUpdateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    //전체보기
    @GetMapping
    public String findAll(Model model) {
        List<BoardListResponseDto> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
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
            System.out.println("===error===");
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
        model.addAttribute("boards", dto);
        return "/boards/edit";
    }

    //업데이트
    @PutMapping("/edit/{id}")
    public String editBoard(@PathVariable Long id, BoardUpdateRequestDto dto) {
        boardService.update(id, dto);
        return "redirect:/boards";
    }
}
