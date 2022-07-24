package now.comento.conow.web.controller;


import lombok.RequiredArgsConstructor;
import now.comento.conow.domain.Board;
import now.comento.conow.service.BoardServiceImpl;
import now.comento.conow.web.dto.BoardDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        List<BoardDto> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "/boards/list";
    }

    //한개만 보기
    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id, Model model) {
        BoardDto dto = boardService.findById(id);
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
    public String post(@ModelAttribute("dto") BoardDto dto) {
        boardService.save(dto);
        return "/boards/list";
    }

    //삭제
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "/boards/list";
    }
}
