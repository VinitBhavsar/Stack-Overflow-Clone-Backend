package com.github.QueNAns.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.github.QueNAns.model.Answer;
import com.github.QueNAns.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int postAnswer(Answer answer) {
		String sql = "insert into ANSWERS (QUESTION_ID, USER_ID, FIRSTNAME, LASTNAME, ANSWER) "
				+ "values ('"+answer.getQuestionid()+"','"+answer.getUserid()+"','"+answer.getFirstname()+"','"+answer.getLastname()+"','"+answer.getAnswer()+"')";
		return jdbcTemplate.queryForObject(sql, Integer.class);
		}

	@Override
	public int deleteAnswer(long ansid) {
		String sql = "delete from ANSWERS where ANSWER_ID='"+ansid+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int updateAnswer(Answer answer) {
		String sql = "update ANSWERS set ANSWER='"+answer.getAnswer()+"' where ANSWER_ID='"+answer.getAnswerid()+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
		}

	@Override
	public List<Answer> getAllAnswersOfQuestion(long questionid) {
		String sql = "select ans.ANSWER_ID, ans.QUESTION_ID, ans.ANSWER, ans.FIRSTNAME, ans.LASTNAME, ans.USER_ID from ANSWERS ans "
				+ "where ans.QUESTION_ID='"+questionid+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Answer>>() {

			@Override
			public List<Answer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Answer> list = new ArrayList<Answer>();
				while(rs.next()) {
					Answer ans = new Answer();
					
					ans.setAnswerid(rs.getLong("ANSWER_ID"));
					ans.setQuestionid(rs.getLong("QUESTION_ID"));
					ans.setAnswer(rs.getString("ANSWER"));
					ans.setFirstname(rs.getString("FIRSTNAME"));
					ans.setLastname(rs.getString("LASTNAME"));
					ans.setUserid(rs.getLong("USER_ID"));
					
					list.add(ans);
				}
				return list;
			}
			
		});
	}

	@Override
	public int updateDeletedUserAnswers(long userid, String randomNumber) {
		String sql = "UPDATE ANSWERS SET FIRSTNAME=USER AND LASTNAME='"+randomNumber+"' WHERE USER_ID='"+userid+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
