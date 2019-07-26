package com.github.QueNAns.service;

import java.util.List;

import com.github.QueNAns.model.Answer;

public interface AnswerService {

	int postAnswer(Answer answer);
	int deleteAnswer(long ansid);
	List<Answer> getAllAnswersOfQuestion(long questionid);
	int updateAnswer(Answer answer);
	int updateDeletedUserAnswers(long userid, String randomNumber);
}
