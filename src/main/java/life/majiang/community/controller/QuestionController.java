package life.majiang.community.controller;


import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model

                           ){

        QuestionDTO questionDTO = questionService.getById(id);
//        HttpSession session = request.getSession();
//        System.out.println(session.toString());
        model.addAttribute("question",questionDTO);

        return "question";

    }
}
