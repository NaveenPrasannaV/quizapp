package org.nit.quizapp.service;

import org.nit.quizapp.dao.QuizDao;
import org.nit.quizapp.feign.QuizInterface;
import org.nit.quizapp.model.QuestionWrapper;
import org.nit.quizapp.model.Quiz;
import org.nit.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {

        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();

        List<QuestionWrapper> questionsFromId = quizInterface.getQuestionsFromId(questionIds).getBody();

        return new ResponseEntity<>(questionsFromId, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Integer score = quizInterface.getScore(responses).getBody();
        return ResponseEntity.ok(score);
    }
}
