package com.github.QueNAns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.QueNAns.model.AnswerCountAPI;
import com.github.QueNAns.model.Question;
import com.github.QueNAns.service.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@CrossOrigin("*")
	@PostMapping("/createQuestion")
	public ResponseEntity<Integer> createQuestion(@RequestBody Question question){
		int response = questionService.createQuestion(question) ;
		
		if(response > 0 ) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@GetMapping("/getAllQuestions")
	public ResponseEntity<List<AnswerCountAPI>> getAllQuestions(){
		List<AnswerCountAPI> response = questionService.getAllQuestions();
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@GetMapping("/getAllQuestionsByUid/{uid}")
	public ResponseEntity<List<AnswerCountAPI>> getAllQuestionsByUid(@PathVariable long uid){
		List<AnswerCountAPI> response = questionService.getAllQuestionsByUserId(uid);
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@DeleteMapping("/deleteQuestion/{qid}")
	public ResponseEntity<Integer> deleteQuestion(@PathVariable long qid){
		int response = questionService.deleteQuestion(qid);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@GetMapping("/getQuestionDetailsByQid/{qid}")
	public ResponseEntity<List<Question>> getQuestionDetails(@PathVariable long qid){
		List<Question> response = questionService.getQuestionDetails(qid);
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}
	
	@CrossOrigin("*")
	@GetMapping("/getMostAnswered")
	public ResponseEntity<List<AnswerCountAPI>> getMostAnswered(){
		List<AnswerCountAPI> response = questionService.getMostAnsweredQuestion();
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}
	
	@CrossOrigin("*")
	@GetMapping("/getUnAnswered")
	public ResponseEntity<List<AnswerCountAPI>> getUnAnswered(){
		List<AnswerCountAPI> response = questionService.getUnAnsweredQuestion();
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}
	
	@CrossOrigin("*")
	@GetMapping("/getSearchedQuestion/{question}")
	public ResponseEntity<List<Question>> getSearchedQuestion(@PathVariable String question){
		List<Question> response = questionService.getSearchedQuestion(question);

		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}
}
