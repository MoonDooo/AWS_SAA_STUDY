package com.aws.saa_c03.controller;


import com.aws.saa_c03.controller.dto.MemoDto;
import com.aws.saa_c03.controller.dto.QuestionDto;
import com.aws.saa_c03.controller.dto.ResponsePage;
import com.aws.saa_c03.controller.dto.Result;
import com.aws.saa_c03.dto.GptQuestionDto;
import com.aws.saa_c03.dto.QuestionDtoWithMemo;
import com.aws.saa_c03.dto.SaveQuestionDto;
import com.aws.saa_c03.service.GPTService;
import com.aws.saa_c03.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/q")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    private final GPTService gptService;

    @PostMapping
    @ResponseBody
    public Map<String, String> saveQuestion(@RequestBody SaveQuestionDto question) {
        questionService.save(question);
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/q/form"); // 리다이렉트할 URL
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, String> updateQuestion(@RequestBody SaveQuestionDto question, @PathVariable int id) {
        questionService.update(question, id);
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/q/"+id); // 리다이렉트할 URL
        return response;
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Result<QuestionDtoWithMemo>> findByIdApi(@PathVariable Integer id) {
        return Result.isSuccess(questionService.findById(id));
    }

    @PostMapping("/api/memo")
    @ResponseBody
    public ResponseEntity<Result<Integer>> findByIdMemo(@RequestBody MemoDto dto) {
        return Result.isSuccess(questionService.saveMemo(dto.getId(), dto.getMemo()));
    }

    @GetMapping("/form")
    public String form(ModelMap model) {
        model.addAttribute("question", null);
        return "form";
    }

    @GetMapping("/form/{id}")
    public String form(@PathVariable int id, ModelMap model) {
        model.addAttribute("question", questionService.findById(id));
        return "form";
    }

    @GetMapping("/list")
    public String listQuestions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int perPage, ModelMap model) {
        ResponsePage<QuestionDto> questionListDtos = questionService.findAll(PageRequest.of(page, perPage));
        model.addAttribute("question", questionListDtos);
        return "list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable int id, ModelMap model) {
        model.addAttribute("question", questionService.findById(id));
        return "view";
    }

    @GetMapping("/gpt")
    @ResponseBody
    public GptQuestionDto gpt(@RequestBody GptQuestionDto dto) {
        return gptService.translate(dto);
    }


}
