package com.qa.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.main.model.Questionx;
import com.qa.main.repo.QuestionxRepository;

@Service
public class QuestionxManager {

	@Autowired
	QuestionxRepository questionxrepository;

	public void save(Questionx question) {
		questionxrepository.save(question);
	}
	
	public Questionx get(String id) {
		Optional<Questionx> q = questionxrepository.findById(id);
		return q.get();
	}
}
