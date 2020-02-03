package dao.impl;

import bean.Vacancy;
import dao.Dao;
import dao.VacancyDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JdbcVacancyDao extends Dao<Vacancy> implements VacancyDao {
    @Override
    public boolean create(Vacancy vacancy) {
        try {

            String sqlInsertUser = "INSERT INTO vacancy (name, description) values (?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlInsertUser);
            preparedStatement.setString(1, vacancy.getName());
            preparedStatement.setString(2, vacancy.getDescription());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Vacancy read(Vacancy vacancy) {
        Vacancy newVacancy = null;
        try {

            String sqlInsertUser = "SELECT * from vacancy where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlInsertUser);
            preparedStatement.setString(1, String.valueOf(vacancy.getId()));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newVacancy=vacancyMap(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return newVacancy;
    }

    private Vacancy vacancyMap(ResultSet resultSet) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(resultSet.getInt("id"));
        vacancy.setName(resultSet.getString("name"));
        vacancy.setDescription(resultSet.getString("description"));
        return vacancy;
    }

    @Override
    public Vacancy update(Vacancy vacancy) {
        String sqlInsertUser = "UPDATE vacancy set name = ? , description = ? where id = ? ";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlInsertUser);
            preparedStatement.setString(1, vacancy.getName());
            preparedStatement.setString(2, vacancy.getDescription());
            preparedStatement.setInt(3, vacancy.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancy;
    }

    @Override
    public Vacancy delete(Vacancy vacancy) {
        String sqlInsertUser = "DELETE  from vacancy where id = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlInsertUser);
            preparedStatement.setInt(1, vacancy.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Vacancy> getAllVacancy() {
        List<Vacancy> vacancies = new LinkedList<>();
        String query = "select * from vacancy";

        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(query);
            while (resultSet.next()) {
                vacancies.add(vacancyMap(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancies;
    }
}
