package org.me.learning.questionmicros.repo;

import org.me.learning.questionmicros.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {



        List<Question> findByCategory(String category);

        @Query(value = "SELECT q.id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
        List<Integer> findRandomQuestionsByCategory(String category, int numQ);
    }

