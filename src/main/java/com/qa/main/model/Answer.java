package com.qa.main.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Answer {
	@Id
	private String id;

	public Integer point;

	private String questionId;

	public Answer(Integer point, String questionId, @NotNull @Size(min = 1, max = 30) String subject) {
		super();
		this.point = point;
		this.questionId = questionId;
		this.subject = subject;
	}

	public Answer() {
		super();
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull
	@Size(min = 1, max = 30)
	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getQuestionid() {
		return questionId;
	}

	public void setQuestionid(String questionId) {
		this.questionId = questionId;
	}
}
