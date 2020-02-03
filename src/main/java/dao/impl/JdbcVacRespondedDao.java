package dao.impl;

import bean.dto.VacResponded;
import dao.Dao;
import dao.VacRespondedDao;
import service.VacRespondedService;
import service.impl.VacRespondedServiceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcVacRespondedDao extends Dao<VacResponded> implements VacRespondedDao {
    @Override
    public boolean create(VacResponded vacResponded) {
        VacRespondedService service = new VacRespondedServiceImpl();
        if (!service.isExist(vacResponded)) {
            String query = "insert into vac_responded (user_id,vacancy_id) values (? , ?)";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setInt(1, vacResponded.getUserId());
                preparedStatement.setInt(2, vacResponded.getVacancyId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public VacResponded read(VacResponded vacResponded) {
        VacResponded newVacResponded = null;
        String query = "select * from vac_responded where vacancy_id = ? and user_id = ? ";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, vacResponded.getUserId());
            preparedStatement.setInt(2, vacResponded.getVacancyId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newVacResponded = vacRespondedMap(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newVacResponded;
    }

    @Override
    public VacResponded update(VacResponded vacResponded) {
        return null;
    }

    @Override
    public VacResponded delete(VacResponded vacResponded) {
        return null;
    }

    private VacResponded vacRespondedMap(ResultSet resultSet) throws SQLException {
        VacResponded vacResponded = new VacResponded();
        vacResponded.setId(resultSet.getInt("id"));
        vacResponded.setUserId(resultSet.getInt("user_id"));
        vacResponded.setVacancyId(resultSet.getInt("vacancy_id"));
        return vacResponded;
    }
}
