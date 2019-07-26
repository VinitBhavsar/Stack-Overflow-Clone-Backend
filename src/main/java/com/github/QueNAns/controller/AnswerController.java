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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.QueNAns.model.Answer;
import com.github.QueNAns.service.AnswerService;

@RestController
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	@CrossOrigin("*")
	@PostMapping("/postAnswer")
	public ResponseEntity<Integer> postAnswer(@RequestBody Answer answer){
		int response = answerService.postAnswer(answer);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@DeleteMapping("/deleteAnswer/{ansid}")
	public ResponseEntity<Integer> deleteAnswer(@PathVariable long ansid){
		int response = answerService.deleteAnswer(ansid);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@PutMapping("/updateAnswer")
	public ResponseEntity<Integer> updateAnswer(@RequestBody Answer answer){
		int response = answerService.updateAnswer(answer);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@GetMapping("/getAllAnswersByQid/{qid}")
	public ResponseEntity<List<Answer>> getAllAnswersOfQuestion(@PathVariable long qid){
		List<Answer> response = answerService.getAllAnswersOfQuestion(qid);
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}
}
