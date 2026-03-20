package org.nit.quizapp.dao;

import org.nit.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz, Integer> {


    List<Quiz> getQuizById(Integer id);
}
