package com.qa.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.model.Answer;
import com.qa.main.model.Question;
import com.qa.main.model.Questionx;
import com.qa.main.service.AnswerManager;
import com.qa.main.service.QuestionManager;

@RestController
@RequestMapping("/fullquestion")
public class FullQuestionController {

	@Autowired
	QuestionManager qm;

	@Autowired
	AnswerManager answerManager;

	@GetMapping("/{id}")
	public Questionx getQuestion(@PathVariable String id) {
		Question q = qm.getQuestion(id);
		List<Answer> answers = answerManager.getAnswersByQuestionId(id);

		Questionx qx = new Questionx();
		qx.setSubject(q.getSubject());
		qx.setId(q.getId());

		qx.setAnswers(answers);

		return qx;	
	}

	@GetMapping("")
	public List<Questionx> getQuestions() {
		List<Question> qs = qm.getQuestions();
		List<Questionx> questions = qs.stream().map(q -> {
			Questionx qx = new Questionx();
			final List<Answer> answers = answerManager.getAnswersByQuestionId(q.getId());
			qx.setSubject(q.getSubject());
			qx.setId(q.getId());

			qx.setAnswers(answers);
			return qx;
		}).collect(Collectors.toList());

		return questions;	
	}

	@PostMapping("")
	public Questionx createQuestion(@RequestBody @Valid Questionx questionx) {
		Question q = new Question();
		q.setSubject(questionx.getSubject());

		qm.save(q);
		questionx.setId(q.getId());		
		System.out.println(q);
		questionx.getAnswers().forEach(a->a.setQuestionid(q.getId()));

		answerManager.saveAnswers(questionx.getAnswers());
		return questionx;
	}

	@DeleteMapping("/{id}")
	public boolean deleteQuestion(@PathVariable String id) {
		qm.deleteQuestion(id);
		answerManager.deleteAnswersByQuestionId(id);
		return true;
	}
}
