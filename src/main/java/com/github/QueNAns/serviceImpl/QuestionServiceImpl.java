package com.github.QueNAns.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.github.QueNAns.model.AnswerCountAPI;
import com.github.QueNAns.model.Question;
import com.github.QueNAns.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int createQuestion(Question question) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long date = timestamp.getTime();
		String sql = "insert into QUESTIONS (USER_ID, FIRSTNAME, LASTNAME, QUESTION_TITLE, QUESTION_DESCRIPTION, CREATED_DATE) "
				+ "values (?,?,?,?,?,?)";
	return jdbcTemplate.execute(sql, new PreparedStatementCallback<Integer>() {

		@Override
		public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
			ps.setLong(1, question.getUserid());
			ps.setString(2, question.getFirstname());
			ps.setString(3, question.getLastname());
			ps.setString(4, question.getQuestion());
			ps.setString(5, question.getDescription());
			ps.setLong(6, date);
			return ps.executeUpdate();
		}
	});
	}

	@Override
	public List<AnswerCountAPI> getAllQuestionsByUserId(long uid) {
		String sql = "select que.QUESTION_ID, que.USER_ID,que.FIRSTNAME,que.LASTNAME,que.QUESTION_TITLE,"
				+ "que.QUESTION_DESCRIPTION,que.CREATED_DATE,(select count(ans.ANSWER_ID) from ANSWERS ans"
				+ " where que.QUESTION_ID = ans.QUESTION_ID) as ANSWER_COUNT from QUESTIONS que where que.USER_ID='"+uid+"'"
						+ " group by que.QUESTION_ID order by que.CREATED_DATE DESC;";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<AnswerCountAPI>>() {

			@Override
			public List<AnswerCountAPI> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<AnswerCountAPI> list = new ArrayList<AnswerCountAPI>();
				while(rs.next()) {
					AnswerCountAPI apiResponse = new AnswerCountAPI();
					
					apiResponse.setQuestionid(rs.getLong("QUESTION_ID"));
					apiResponse.setUserid(rs.getLong("USER_ID"));
					apiResponse.setFirstname(rs.getString("FIRSTNAME"));
					apiResponse.setLastname(rs.getString("LASTNAME"));
					apiResponse.setQuestion(rs.getString("QUESTION_TITLE"));
					apiResponse.setDescription(rs.getString("QUESTION_DESCRIPTION"));
					apiResponse.setCreated_date(rs.getLong("CREATED_DATE"));
					apiResponse.setAnswerCount(rs.getInt("ANSWER_COUNT"));
					
					list.add(apiResponse);
				}
				return list;
			}			
		});
	}

	@Override
	public List<AnswerCountAPI> getAllQuestions() {
		String sql = "select que.QUESTION_ID, que.USER_ID,que.FIRSTNAME,que.LASTNAME,que.QUESTION_TITLE,"
				+ "que.QUESTION_DESCRIPTION,que.CREATED_DATE,(select count(ans.ANSWER_ID) from ANSWERS ans"
				+ " where que.QUESTION_ID = ans.QUESTION_ID) as ANSWER_COUNT from QUESTIONS que group by que.QUESTION_ID"
				+ " order by que.CREATED_DATE DESC;";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<AnswerCountAPI>>() {

			@Override
			public List<AnswerCountAPI> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<AnswerCountAPI> list = new ArrayList<AnswerCountAPI>();
				while(rs.next()) {
					AnswerCountAPI apiResponse = new AnswerCountAPI();
					
					apiResponse.setQuestionid(rs.getLong("QUESTION_ID"));
					apiResponse.setUserid(rs.getLong("USER_ID"));
					apiResponse.setFirstname(rs.getString("FIRSTNAME"));
					apiResponse.setLastname(rs.getString("LASTNAME"));
					apiResponse.setQuestion(rs.getString("QUESTION_TITLE"));
					apiResponse.setDescription(rs.getString("QUESTION_DESCRIPTION"));
					apiResponse.setCreated_date(rs.getLong("CREATED_DATE"));
					apiResponse.setAnswerCount(rs.getInt("ANSWER_COUNT"));
					
					list.add(apiResponse);
				}
				return list;
			}			
		});
	}

	@Override
	public int deleteQuestion(long qid) {
		String sql = "delete from QUESTIONS where QUESTION_ID='"+qid+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
		}

	@Override
	public List<Question> getQuestionDetails(long questionid) {
		String sql = "select QUESTION_ID, USER_ID, FIRSTNAME, LASTNAME, QUESTION_TITLE, QUESTION_DESCRIPTION"
				+ " from QUESTIONS where QUESTION_ID='"+questionid+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Question>>() {

			@Override
			public List<Question> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Question> list = new ArrayList<Question>();
				while(rs.next()) {
					Question apiRepo = new Question();
					
					apiRepo.setQuestionid(rs.getLong("QUESTION_ID"));
					apiRepo.setUserid(rs.getLong("USER_ID"));
					apiRepo.setFirstname(rs.getString("FIRSTNAME"));
					apiRepo.setLastname(rs.getString("LASTNAME"));
					apiRepo.setQuestion(rs.getString("QUESTION_TITLE"));
					apiRepo.setDescription(rs.getString("QUESTION_DESCRIPTION"));
					list.add(apiRepo);
				}
				return list;
			}
			
		});
	}

	@Override
	public List<Question> getSearchedQuestion(String question) {
		String sql = "select COUNT(*) from QUESTIONS where QUESTION_TITLE LIKE %'"+question+"'%";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Question>>() {

			@Override
			public List<Question> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Question> list = new ArrayList<Question>();
				while(rs.next()) {
					Question que = new Question();
					
					que.setQuestionid(rs.getLong("QUESTION_ID"));
					que.setUserid(rs.getLong("USER_ID"));
					que.setFirstname(rs.getString("FIRSTNAME"));
					que.setLastname(rs.getString("LASTNAME"));
					que.setQuestion(rs.getString("QUESTION_TITLE"));
					que.setDescription(rs.getString("QUESTION_DESCRIPTION"));
					
					list.add(que);
				}
				return list;
			}
			
		});
	}

	@Override
	public List<AnswerCountAPI> getMostAnsweredQuestion() {
		String sql = "select que.QUESTION_ID, que.USER_ID, que.FIRSTNAME, que.LASTNAME, que.QUESTION_TITLE, que.QUESTION_DESCRIPTION, "
				+ "que.QUESTION_DESCRIPTION, que.CREATED_DATE, count(ans.ANSWER_ID) as ANSWER_COUNT from questions que left outer"
				+ " join answers ans on que.QUESTION_ID = ans.ANSWER_ID group by que.QUESTION_ID having count(ans.ANSWER_ID) > 3";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<AnswerCountAPI>>() {

			@Override
			public List<AnswerCountAPI> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<AnswerCountAPI> list = new ArrayList<AnswerCountAPI>();
				while(rs.next()) {
					AnswerCountAPI apiRes = new AnswerCountAPI();
					
					apiRes.setAnswerCount(rs.getLong("ANSWER_COUNT"));
					apiRes.setQuestionid(rs.getLong("QUESTION_ID"));
					apiRes.setUserid(rs.getLong("USER_ID"));
					apiRes.setFirstname(rs.getString("FIRSTNAME"));
					apiRes.setLastname(rs.getString("LASTNAME"));
					apiRes.setQuestion(rs.getString("QUESTION_TITLE"));
					apiRes.setDescription(rs.getString("QUESTION_DESCRIPTION"));
					apiRes.setCreated_date(rs.getLong("CREATED_DATE"));
					
					list.add(apiRes);
				}
				return list;
			}
			
		});
	}

	@Override
	public List<AnswerCountAPI> getUnAnsweredQuestion() {
		String sql = "select que.QUESTION_ID, que.USER_ID, que.FIRSTNAME, que.LASTNAME, que.QUESTION_TITLE, que.QUESTION_DESCRIPTION,"
				+ " que.QUESTION_DESCRIPTION, que.CREATED_DATE, count(ans.ANSWER_ID) as ANSWER_COUNT from questions que left outer"
				+ " join answers ans on que.QUESTION_ID = ans.ANSWER_ID group by que.QUESTION_ID having count(ans.ANSWER_ID) = 0";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<AnswerCountAPI>>() {

			@Override
			public List<AnswerCountAPI> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<AnswerCountAPI> list = new ArrayList<AnswerCountAPI>();
				while(rs.next()) {
					AnswerCountAPI apiRes = new AnswerCountAPI();
					
					apiRes.setAnswerCount(rs.getLong("ANSWER_COUNT"));
					apiRes.setQuestionid(rs.getLong("QUESTION_ID"));
					apiRes.setUserid(rs.getLong("USER_ID"));
					apiRes.setFirstname(rs.getString("FIRSTNAME"));
					apiRes.setLastname(rs.getString("LASTNAME"));
					apiRes.setQuestion(rs.getString("QUESTION_TITLE"));
					apiRes.setDescription(rs.getString("QUESTION_DESCRIPTION"));
					apiRes.setCreated_date(rs.getLong("CREATED_DATE"));
					
					list.add(apiRes);
				}
				return list;
			}			
		});
	}

	@Override
	public int updateDeleteUserQuestions(long userid, String randomNumber) {
		String sql = "UPDATE QUESTIONS SET FIRSTNAME=USER AND LASTNAME='"+randomNumber+"' where USER_ID='"+userid+"'";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class);
		
		if(result > 0) {
			return 1;
		}
		else {
			return 1;
		}
	}
}