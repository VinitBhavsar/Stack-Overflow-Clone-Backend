package com.github.QueNAns.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.github.QueNAns.model.Answer;
import com.github.QueNAns.model.Question;
import com.github.QueNAns.model.SecurityQuestion;
import com.github.QueNAns.model.User;
import com.github.QueNAns.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<User> login(String emailAddress, String password) {
		//Encryption of Password
		String sql = "select user.USER_ID, user.FIRSTNAME, user.LASTNAME from User user where user.EMAIL_ADDRESS='"+emailAddress+"' and user.PASSWORD='"+password+"'";	
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {

			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User> list = new ArrayList<User>(); 
				while(rs.next()) {
					User user = new User();
					
					user.setUserid(rs.getLong("user_id"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					
					list.add(user);				
				}
					return list;
			}			
		});
		}

	@Override
	public int signUp(User user) {		
		//Encryption of password
		String sql = "insert into user (FIRSTNAME, LASTNAME, EMAIL_ADDRESS, PASSWORD, SQ_ID, SQ_ANSWER) "
				+ "values (?,?,?,?,?,?)";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmailAddress());
				ps.setString(4, user.getPassword());
				ps.setLong(5, user.getSq_id());
				ps.setString(6, user.getSq_answer());
				
				return ps.executeUpdate();
			}
		});
	}

	@Override
	public List<SecurityQuestion> getAllSecQuestions() {
		String sql = "select SQ_ID, SQ_QUESTION from SECURITY_QUESTIONS";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SecurityQuestion>>() {

			@Override
			public List<SecurityQuestion> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<SecurityQuestion> list = new ArrayList<SecurityQuestion>();
				while(rs.next()) {
					SecurityQuestion sq = new SecurityQuestion();
					
					sq.setSq_id(rs.getLong("SQ_ID"));
					sq.setSq_Question(rs.getString("SQ_QUESTION"));
					
					list.add(sq);
				}
				return list;
			}
			
		});
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "select USER_ID, FIRSTNAME, LASTNAME from user";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {

			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User> list = new ArrayList<User>();
				while(rs.next()) {
					
					User user = new User();
					
					user.setUserid(rs.getLong("USER_ID"));
					user.setFirstName(rs.getString("FIRSTNAME"));
					user.setLastName(rs.getString("LASTNAME"));
					
					list.add(user);
				}
				return list;
			}			
		});
	}

	@Override
	public List<User> getProfileDetails(long profileId) {
		String sql = "select USER_ID, FIRSTNAME, LASTNAME from users where USER_ID='"+profileId+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {

			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User> list = new ArrayList<User>();
				while(rs.next()) {
					
					User user = new User();
					
					user.setUserid(rs.getLong("USER_ID"));
					user.setFirstName(rs.getString("FIRSTNAME"));
					user.setLastName(rs.getString("LASTNAME"));
					
					list.add(user);
				}
				return list;
			}			
		});
	}

	@Override
	public List<Question> getProfileQuestions(long profileId) {
		String sql = "select QUESTION_ID, FIRSTNAME, LASTNAME, QUESTION_TITLE, QUESTION_DESCRIPTION, USER_ID from QUESTIONS "
				+ "where USER_ID='"+profileId+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Question>>() {

			@Override
			public List<Question> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Question> list = new ArrayList<Question>();
				while(rs.next()) {
					Question que = new Question();
					
					que.setQuestionid(rs.getLong("QUESTION_ID"));
					que.setFirstname(rs.getString("FIRSTNAME"));
					que.setLastname(rs.getString("LASTNAME"));
					que.setQuestion(rs.getString("QUESTION_TITLE"));
					que.setDescription(rs.getString("QUESTION_DESCRIPTION"));
					que.setUserid(rs.getLong("USER_ID"));
					
					list.add(que);
				}
				return list;
			}
			
		});
	}

	@Override
	public List<Answer> getProfileAnswers(long profileId) {
		String sql = "select ANSWER_ID, QUESTION_ID, ANSWER from ANSWERS where USER_ID='"+profileId+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Answer>>() {

			@Override
			public List<Answer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Answer> list = new ArrayList<Answer>();
				while(rs.next()) {
					Answer ans = new Answer();
					
					ans.setAnswerid(rs.getLong("ANSWER_ID"));
					ans.setQuestionid(rs.getLong("QUESTION_ID"));
					ans.setAnswer(rs.getString("ANSWER"));
					
					list.add(ans);
				}
				return list;
			}
		});
	}
	
	@Override
	public int checkEmailAddress(User user) {
		String sql = "select EMAIL_ADDRESS, SQ_ID, SQ_ANSWER FROM USERS WHERE EMAIL_ADDRESS=?, SQ_ID=?, SQ_ANSWER=?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, user.getEmailAddress());
				ps.setLong(2, user.getSq_id());
				ps.setString(3, user.getSq_answer());
				return ps.executeUpdate();
			}
		});
	}

	@Override
	public int resetPassword(User user) {
		String sql = "UPDATE USERS SET PASSWORD='"+user.getPassword()+"' WHERE EMAIL_ADDRESS='"+user.getEmailAddress()+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public int updateUserPersonalDetails(User user) {
		String sql = "UPDATE USERS SET FIRSTNAME='"+user.getFirstName()+"' and LASTNAME='"+user.getLastName()+"' where USER_ID='"+user.getUserid()+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int updateUserEmailAddress(User user) {
		String sql = "UPDATE USERS SET EMAIL_ADDRESS='"+user.getEmailAddress()+"' where USER_ID='"+user.getUserid()+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int updateUserPassword(User user) {
		String sql = "UPDATE USER SET PASSWORD='"+user.getPassword()+"' where USER_ID='"+user.getUserid()+"' ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int updateUserProfilePicture(User user) {
		//Apache Server Currently Not Installed
		//Code Will be updated in next week
		return 0;
	}

	@Override
	public int getPassword(User user) {
		String sql = "select PASSWORD from USERS where USER_ID=?";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setLong(1, user.getUserid());
				return ps.executeUpdate();
			}
		});
	}

	@Override
	public int deleteUser(long userid) {
		String sql = "DELETE FROM USERS WHERE USER_ID='"+userid+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
