package com.qa.main.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.qa.main.model.Answer;

public interface AnswerRepository extends MongoRepository<Answer, String> {

	public List<Answer> findByQuestionId(String questionId);
	
	public Optional<Answer> findById(String id);
	
	public void deleteByIdIn(List<String> ids);
	
	public void deleteByQuestionId(String id);
}