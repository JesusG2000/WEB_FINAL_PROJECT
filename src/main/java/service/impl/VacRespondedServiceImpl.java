package service.impl;

import bean.dto.VacResponded;
import dao.Dao;
import service.VacRespondedService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VacRespondedServiceImpl implements VacRespondedService {
    @Override
    public boolean isExist(VacResponded vacResponded) {
        String query = "select * from vac_responded where user_id = ? and vacancy_id = ?";

        try {
            PreparedStatement statement = Dao.getConnection().prepareStatement(query);
            statement.setInt(1,vacResponded.getUserId());
            statement.setInt(2,vacResponded.getVacancyId());
            statement.executeQuery();

            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
           return resultSet.getRow()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
