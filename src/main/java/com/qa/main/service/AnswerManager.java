package com.qa.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.main.model.Answer;
import com.qa.main.repo.AnswerRepository;

@Service
public class AnswerManager {

	AnswerRepository repo;

	public AnswerManager(AnswerRepository answerrepository) {
		super();
		this.repo = answerrepository;
	}

	public Answer getAnswer(String id) {
		Optional<Answer> a = repo.findById(id);
		return a.get();
	}
	
	public Answer saveAnswer(Answer answer) {
		return repo.save(answer);
	}
	
	public void saveAnswers(Iterable<Answer> answers) {
		repo.saveAll(answers);
	}

	public List<Answer> getAnswersByQuestionId(String questionId) {
		List<Answer> a = repo.findByQuestionId(questionId);
		return a;
	}
	
	public boolean deleteAnswer(String id) {
		repo.deleteById(id);
		return true;
	}
	
	public boolean deleteAnswersByQuestionId(String id) {
		repo.deleteByQuestionId(id);
		return true;
	}
}
