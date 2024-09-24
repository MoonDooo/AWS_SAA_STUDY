package com.aws.saa_c03.repository;

import com.aws.saa_c03.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.memo m LEFT JOIN FETCH q.view v WHERE q.id = :id")
    Optional<Question> findByIdWithView(Integer id);
}
