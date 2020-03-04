package by.epam.service;

import by.epam.bean.Resume;
import by.epam.exception.ServiceException;

public interface ResumeService extends Service<Resume> {
    Resume readByUserIdVacancyId(int userId, int vacancyId) throws ServiceException;
}
