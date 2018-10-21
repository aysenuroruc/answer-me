package com.qa.main.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qa.main.model.Questionx;

public interface QuestionxRepository extends  MongoRepository<Questionx, String> {

	
}
