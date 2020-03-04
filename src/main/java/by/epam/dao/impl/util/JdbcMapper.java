package by.epam.dao.impl.util;

import by.epam.bean.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcMapper {
    public Interview interviewMap(ResultSet resultSet) throws SQLException {
        Interview interview = new Interview();
        interview.setId(resultSet.getInt("id"));
        interview.setHrId(resultSet.getInt("hr_id"));
        interview.setSeekerId(resultSet.getInt("seeker_id"));
        interview.setDate(resultSet.getDate("date"));
        interview.setComment(resultSet.getString("comment"));
        interview.setVacancyId(resultSet.getInt("vacancy_id"));
        return interview;

    }

    public Message messageMap(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt("id"));
        message.setSenderId(resultSet.getInt("sender_id"));
        message.setGetterId(resultSet.getInt("getter_id"));
        message.setContent(resultSet.getString("content"));
        return message;
    }

    public User userMap(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name").trim());
        user.setPassword(resultSet.getString("password").trim());
        user.setRole(Role.valueOf(resultSet.getString("role_name").toUpperCase().trim()));
        return user;
    }

    public Vacancy vacancyMap(ResultSet resultSet) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(resultSet.getInt("id"));
        vacancy.setName(resultSet.getString("name"));
        vacancy.setDescription(resultSet.getString("description"));
        return vacancy;
    }

    public VacResponded vacRespondedMap(ResultSet resultSet) throws SQLException {
        VacResponded vacResponded = new VacResponded();
        vacResponded.setId(resultSet.getInt("id"));
        vacResponded.setUserId(resultSet.getInt("user_id"));
        vacResponded.setVacancyId(resultSet.getInt("vacancy_id"));
        return vacResponded;
    }

    public Resume resumeMap(ResultSet resultSet) throws SQLException {
        Resume resume = new Resume();

        resume.setId(resultSet.getInt("id"));
        resume.setUserId(resultSet.getInt("user_id"));
        resume.setVacancyId(resultSet.getInt("vacancy_id"));
        resume.setUserName(resultSet.getString("user_name"));
        resume.setUserSurname(resultSet.getString("user_surname"));
        resume.setUserAge(resultSet.getInt("user_age"));
        resume.setUserLanguage(resultSet.getString("user_language"));
        resume.setUserWorkExperience(resultSet.getString("user_work_experience"));
        resume.setUserInfo(resultSet.getString("user_info"));

        return resume;
    }
}
