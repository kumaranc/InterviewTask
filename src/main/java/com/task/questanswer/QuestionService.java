package com.task.questanswer;

import com.task.Responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public PostResponse createQuestion(Question question) {
        Question question1 = questionRepository.save(question);
        return new PostResponse(question1.getId(), "200", "Question has been created", "Success");
    }

    public void getQuestion() {

    }
}
