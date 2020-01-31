package dao;

import bean.User;
import bean.Vacancy;

import java.util.List;

public interface UserDao {
    User getUserByName(String name);
    List<User> getAllSeekers();
    List<User> getAllHR();
    List<User> getAllByVacancyId(Vacancy vacancy);
}
