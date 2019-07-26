package com.github.QueNAns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.QueNAns.model.Answer;
import com.github.QueNAns.model.Question;
import com.github.QueNAns.model.User;
import com.github.QueNAns.service.UserService;

@RestController
public class UserController {

	@Autowired(required=true)
	UserService userService;
	
	@CrossOrigin("*")
	@GetMapping("/login/{emailAddress}/{password}")
	public ResponseEntity<List<User>> userLogin(@PathVariable String emailAddress, @PathVariable String password){
		List<User> response = userService.login(emailAddress, password);
		boolean check = response.isEmpty();
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@PostMapping("/signup")
	public ResponseEntity<Integer> userSignUp(@RequestBody User user){
		int response = userService.signUp(user);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@PostMapping("/forgetPass")
	public ResponseEntity<Integer> checkDetails(@RequestBody User user){
		int response = userService.checkEmailAddress(user);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@PutMapping("/reset-pass")
	public ResponseEntity<Integer> resetPassword(@RequestBody User user){
		int response = userService.resetPassword(user);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	@CrossOrigin("*")
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> response = userService.getAllUsers();
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@GetMapping("/getProfileDetail/{profileId}")
	public ResponseEntity<List<User>> getProfileDetail(@PathVariable long profileId){
		List<User> response = userService.getProfileDetails(profileId);
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}		
	}
	
	@CrossOrigin("*")
	@GetMapping("/getProfileQuestions/{profileId}")
	public ResponseEntity<List<Question>> getProfileQuestions(@PathVariable long profileId){
		List<Question> response = userService.getProfileQuestions(profileId);
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@GetMapping("/getProfileAnswers/{profileId}")
	public ResponseEntity<List<Answer>> getProfileAnswers(@PathVariable long profileId){
		List<Answer> response = userService.getProfileAnswers(profileId);
		
		boolean check = response.isEmpty();
		
		if(check != true) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@CrossOrigin("*")
	@PutMapping("/updatePersonalDetails")
	public ResponseEntity<Integer> updatePersonalDetails(@RequestBody User user){
		int response = userService.updateUserPersonalDetails(user);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}
	
	@CrossOrigin("*")
	@PutMapping("/updateEmailAddress")
	public ResponseEntity<Integer> updateEmailAddress(@RequestBody User user){
		int response = userService.updateUserEmailAddress(user);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}
	
	@CrossOrigin("*")
	@PostMapping("/getPassword")
	public ResponseEntity<Integer> getPassword(@RequestBody User user){
		int response = userService.getPassword(user);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}
	
	@CrossOrigin("*")
	@PutMapping("/updatePassword")
	public ResponseEntity<Integer> updatePassword(@RequestBody User user){
		int response = userService.updateUserPassword(user);
		
		if(response > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}
}
