package by.epam.service;

import by.epam.bean.Vacancy;
import by.epam.exception.ServiceException;

import java.util.List;

public interface VacancyService extends Service<Vacancy> {
    List<Vacancy> getAllVacancy() throws ServiceException;
}
