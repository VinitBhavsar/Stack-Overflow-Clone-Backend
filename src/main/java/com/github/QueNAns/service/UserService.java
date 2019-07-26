package com.github.QueNAns.service;

import java.util.List;

import com.github.QueNAns.model.Answer;
import com.github.QueNAns.model.Question;
import com.github.QueNAns.model.SecurityQuestion;
import com.github.QueNAns.model.User;

public interface UserService {
	
	List<User> login(String emilAddress, String password);
	int signUp(User user);
	
	//ForgetPassword Service
	List<SecurityQuestion> getAllSecQuestions();
	int checkEmailAddress(User user);
	int resetPassword(User user);
	
	//Common Service
	List<User> getAllUsers();
	List<User> getProfileDetails(long profileId);
	List<Question> getProfileQuestions(long profileId);
	List<Answer> getProfileAnswers(long profileId);
	
	//User Details Updating Service
	int updateUserPersonalDetails(User user);
	int updateUserEmailAddress(User user);
	int getPassword(User user);
	int updateUserPassword(User user);
	int updateUserProfilePicture(User user);
	
	int deleteUser(long userid);
}
