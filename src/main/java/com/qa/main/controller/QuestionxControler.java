package com.qa.main.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.model.Answer;
import com.qa.main.model.Questionx;
import com.qa.main.repo.QuestionxRepository;
import com.qa.main.service.QuestionxManager;

@RestController
@RequestMapping("/questionx")
public class QuestionxControler {

	private final Logger            LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	QuestionxRepository questionxrepository;
	
	@Autowired
	QuestionxManager questionxmanager;

    @PostMapping("")
    public Questionx createQuestion(@RequestBody Questionx questionx) {
        LOG.debug("[createQuestion]: Question: {}", questionx);
        questionxmanager.save(questionx);
        
        return questionx;
    }
    // Bu methoda request atabilirmisin atarsan rest api urli nedir ?
    @GetMapping("{id}")
    public Questionx getQuestion(@PathVariable String id) {
    	
        return questionxmanager.get(id);
    }
    
    @DeleteMapping("{id}/answers/{answerid}")
    public Questionx deleteAnswer(@PathVariable String id, @PathVariable String answerid) {
    	Questionx q = getQuestion(id);
    	Iterator<Answer> answers = q.getAnswers().iterator();
    	while (answers.hasNext()) {
			Answer a = (Answer) answers.next();
			
			if (a.getId().equals(answerid)) {
				answers.remove();
			}
		}
    	
    	questionxrepository.save(q);
    	
    	return q;
    }
    
}
