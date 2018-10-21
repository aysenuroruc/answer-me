package com.qa.main;

import com.qa.main.model.Answer;

public class DataUtils {

	public static Answer createAnswer() {
		Answer answer = new Answer(3, "testid", "subject");

		return answer;

	}

}
