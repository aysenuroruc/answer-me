package com.qa.main.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qa.main.model.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {

}
