package com.github.QueNAns.service;

import java.util.List;

import com.github.QueNAns.model.AnswerCountAPI;
import com.github.QueNAns.model.Question;

public interface QuestionService {

	int createQuestion(Question question);
	List<AnswerCountAPI> getAllQuestions();
	List<Question> getQuestionDetails(long questionid);
	int deleteQuestion(long qid);
	List<AnswerCountAPI> getAllQuestionsByUserId(long uid);
	List<Question> getSearchedQuestion(String question);	
	List<AnswerCountAPI> getMostAnsweredQuestion();
	List<AnswerCountAPI> getUnAnsweredQuestion();
	int updateDeleteUserQuestions(long userid, String randomNumber);
}
