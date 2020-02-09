package service;

import bean.Vacancy;
import exception.ServiceException;

import java.util.List;

public interface VacancyService extends Service<Vacancy> {
    List<Vacancy> getAllVacancy() throws ServiceException;
}
