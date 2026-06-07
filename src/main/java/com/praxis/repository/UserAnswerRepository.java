package com.praxis.repository;

import com.praxis.model.Question;
import com.praxis.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findByUserSession_Id(Long sessionId);
    List<UserAnswer> findByQuestion_Id(Long questionId);
}
