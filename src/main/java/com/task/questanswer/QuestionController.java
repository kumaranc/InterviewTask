package com.task.questanswer;

import com.task.Responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping(path = "/api/v2/question/create")
    public ResponseEntity<?> createQuestion(Question question) {
        PostResponse postResponse = questionService.createQuestion(question);
        return ResponseEntity.ok(postResponse);
    }
}
