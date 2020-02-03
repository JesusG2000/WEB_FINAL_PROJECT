package dao.impl;

import bean.Interview;
import bean.User;
import dao.Dao;
import dao.InterviewDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JdbcInterviewDao extends Dao<Interview> implements InterviewDao {
    @Override
    public boolean create(Interview interview) {
        String query = "INSERT into interview (hr_id, seeker_id, date, comment ,vacancy_id) values (?,?,?,?,?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, interview.getHrId());
            statement.setInt(2, interview.getSeekerId());
            statement.setDate(3, interview.getDate());
            statement.setString(4, interview.getComment());
            statement.setInt(5, interview.getVacancyId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Interview read(Interview interview) {
        return null;
    }

    @Override
    public Interview update(Interview interview) {
        return null;
    }

    @Override
    public Interview delete(Interview interview) {
        return null;
    }

    @Override
    public List<Interview> getAllInterviewBySeekerId(User user) {
        List<Interview> list = new LinkedList<>();
        String query = "select * from interview  where seeker_id = ? ";

        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, user.getId());


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(interviewMap(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Interview interviewMap(ResultSet resultSet) throws SQLException {
        Interview interview = new Interview();
        interview.setId(resultSet.getInt("id"));
        interview.setHrId(resultSet.getInt("hr_id"));
        interview.setSeekerId(resultSet.getInt("seeker_id"));
        interview.setDate(resultSet.getDate("date"));
        interview.setComment(resultSet.getString("comment"));
        interview.setVacancyId(resultSet.getInt("vacancy_id"));
        return interview;

    }
}
