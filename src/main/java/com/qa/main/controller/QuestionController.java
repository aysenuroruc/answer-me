package com.qa.main.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.model.Question;
import com.qa.main.model.ResultMessages;
import com.qa.main.repo.QuestionRepository;
import com.qa.main.service.QuestionManager;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	private final Logger            LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	QuestionManager qm;

	@Autowired
	QuestionRepository repo;


	@PostMapping("")
	public Question createQuestion(@RequestBody Question question) {
		LOG.debug("[createQuestion]: Question: {}", question);
		qm.save(question);

		return question;
	}

	@DeleteMapping("/{id}")
	public String deleteQuestion(@PathVariable String id) {
		repo.deleteById(id);
		ResultMessages rm = new ResultMessages();
		return rm.OK;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateQuestion(@RequestBody Question question, @PathVariable String id){
		Optional<Question> question2 = repo.findById(id);
		if (!question2.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		question.setId(id);
		repo.save(question);
		return ResponseEntity.noContent().build(); 

	}

	@GetMapping("/{id}")
	public Question getQuestion(@PathVariable String id) {
		Question q = repo.findById(id).get();
		return q;
	}

	@GetMapping("")
	public List<Question> getAllQuestion() {
		return repo.findAll();

	}


}
