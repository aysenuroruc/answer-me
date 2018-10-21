package com.qa.main.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qa.main.model.Answer;
import com.qa.main.model.AnswerAction;

public interface AnswerActionRepository extends MongoRepository<AnswerAction, String> {

	public AnswerAction findByAnswerId(String id);
	public void deleteByAnswerId(String id);
}