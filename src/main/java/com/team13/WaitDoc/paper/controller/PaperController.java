package com.team13.WaitDoc.paper.controller;

import com.team13.WaitDoc.member.service.MemberService;
import com.team13.WaitDoc.paper.dto.PaperDto;
import com.team13.WaitDoc.paper.entity.Paper;
import com.team13.WaitDoc.paper.repository.PaperRepository;
import com.team13.WaitDoc.paper.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequestMapping("/paper")
@RequiredArgsConstructor
public class PaperController {
    private final PaperService paperService;



    @GetMapping("/register")
    public String RegisterPaper() {
        return "paper/paperRegister";
    }

    @PostMapping("/registerPro")
    public String RegisterPaperPro(@ModelAttribute PaperDto paperDto) {

        String url = paperService.upload(paperDto.getFile(), UUID.randomUUID().toString());
        paperService.register(paperDto, url);

        return "redirect:/paper/list";
    }

    @GetMapping("/detail/{id}")
    public String viewDetailPaper(Model model, @PathVariable(name = "id") Long productId) {

        Paper paper= paperService.findById(productId);
        model.addAttribute("paper", paper);

        return "paper/paperDetail";
    }

    @GetMapping("/list")
    public  String paperList(Model model){
        List<Paper> papers = paperService.findAll();

        model.addAttribute("papers", papers);
        return "paper/paperList";
    }

    @GetMapping("/delete/{id}")
    public String deletePaper(@PathVariable(name = "id") Long paperId) {
        paperService.delete(paperId);
        return "redirect:/paper/list";
    }



}
