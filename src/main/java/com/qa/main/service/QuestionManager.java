package com.qa.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.main.model.Question;
import com.qa.main.repo.QuestionRepository;

@Service
public class QuestionManager {

	@Autowired
	QuestionRepository repo;

	public Question save(Question question) {
		return repo.save(question);
	}
	  
 
    public Question getQuestion(String id) {
        Question q = repo.findById(id).get();
        return q;
    }
    
    public List<Question> getQuestions() {
    	List<Question> q = repo.findAll();
        return q;
    }
    
    public boolean deleteQuestion(String id) {
        repo.deleteById(id);
        return true;
    }
    
}
