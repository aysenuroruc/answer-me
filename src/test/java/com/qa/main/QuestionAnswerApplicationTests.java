package com.qa.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.main.model.Answer;
import com.qa.main.repo.AnswerRepository;
import com.qa.main.service.AnswerManager;

//import scala.throws;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuestionAnswerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class QuestionAnswerApplicationTests {

	AnswerManager answerManager;

	@MockBean
	AnswerRepository answerRepository;

	@Before
	public void setup() {
		answerManager = new AnswerManager(answerRepository);
	}

	@Test
	public void shouldReturnModelWhenGetAnswer() {
		Optional<Answer> result = Optional.of(DataUtils.createAnswer());
		Mockito.when(answerRepository.findById(anyString())).thenReturn(result);
		Answer request = answerManager.getAnswer(anyString());
		assertThat(request).isEqualTo(result.get());

		// Jedis jedis = Mockito.mock(Jedis.class);
		// TimeseriesData timeseriesData = DataUtils.generateTimeSeries();
		// Mockito.when(jedisPool.getResource()).thenReturn(jedis);
		// jedisRepository.add(timeseriesData.getInstances().get(0),
		// timeseriesData.getDeviceId(), timeseriesData.getTimestamp(),
		// timeseriesData.getData());
		// Mockito.verify(jedis, times(1)).zadd(anyString(), anyDouble(), anyString());
	}
	
	@Test
	public void shouldReturnAnswerWithQuestionid() {
		List<Answer> answerList = new ArrayList<>();
		answerList.add(DataUtils.createAnswer());
		Mockito.when(answerRepository.findByQuestionId(anyString())).thenReturn(answerList);
		List<Answer> result = answerManager.getAnswersByQuestionId(anyString());
		assertThat(result).isEqualTo(answerList);
		Mockito.verify(answerRepository, times(1)).findByQuestionId(anyString());
	}

	// @SuppressWarnings("serial")
	// @Test(expected = JsonProcessingException.class)
	// public void shouldReturnJsonProcessingExceptionWhenParseData() throws
	// JsonProcessingException {
	// Jedis jedis = Mockito.mock(Jedis.class);
	//
	// Mockito.when(jedisPool.getResource()).thenReturn(jedis);
	// TimeseriesData timeseriesData = DataUtils.generateTimeSeries();
	// Mockito.when(Utils.mapper.writeValueAsString(any(Object.class))).thenThrow(new
	// JsonProcessingException("Error") {
	// });
	// jedisRepository.add(timeseriesData.getInstances().get(0),
	// timeseriesData.getDeviceId(), timeseriesData.getTimestamp(), new Object());
	// }

}
