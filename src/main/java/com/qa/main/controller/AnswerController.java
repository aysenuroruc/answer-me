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

import com.qa.main.model.Answer;
import com.qa.main.repo.AnswerRepository;
import com.qa.main.service.AnswerManager;

//import net.wimpi.telnetd.io.terminal.ansi;

//import scala.annotation.meta.field;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	 private final Logger            LOG = LoggerFactory.getLogger(this.getClass());
	 
	 @Autowired
	 AnswerManager answermanager;
	 
	 @Autowired
	 AnswerRepository answerrepository;
	 
	 @PostMapping("")
	 public Answer createAnswer(@RequestBody Answer answer   ) {
		  LOG.debug("[createAnswer]: Answer: {}", answer);
		 answerrepository.save(answer);
		return answer;
	 }
	 
	 @DeleteMapping("/answer/{id}")
	 public void deleteStudent(@PathVariable String id) {
		 answerrepository.deleteById(id);
	 }
	 
	 @PutMapping("/answer/{id}")
	    public ResponseEntity<Object> updateAnswer(@RequestBody Answer answer, @PathVariable String id){
	    	Optional<Answer> answer2 = answerrepository.findById(id);
	    	if (!answer2.isPresent()) 
	    		return ResponseEntity.notFound().build();
	    		answer.setId(id);	
	    		answerrepository.save(answer);
	    		return ResponseEntity.noContent().build(); 	
	}
	 
	 @GetMapping("/{id}")
	 public Answer getAnswer(@PathVariable String id) {
	    Optional<Answer> a = answerrepository.findById (id);
		return a.get();
	}
	 
	 @GetMapping("")
	 public List<Answer> getAnswers() {
	    List<Answer> a = answerrepository.findAll();
		return a;
	}
	 
 }
