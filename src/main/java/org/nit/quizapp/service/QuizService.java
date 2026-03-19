package org.nit.quizapp.service;

import org.nit.quizapp.dao.QuestionDao;
import org.nit.quizapp.dao.QuizDao;
import org.nit.quizapp.model.Question;
import org.nit.quizapp.model.QuestionWrapper;
import org.nit.quizapp.model.Quiz;
import org.nit.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        System.out.println(questions.size());

        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {

        Quiz quiz = quizDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question question : quiz.getQuestions()) {

            questionsForUser.add(
                    new QuestionWrapper(
                            question.getId(),
                            question.getQuestionTitle(),
                            question.getOption1(),
                            question.getOption2(),
                            question.getOption3(),
                            question.getOption4()
                    )
            );
        }

        return ResponseEntity.ok(questionsForUser);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);

    }
}
