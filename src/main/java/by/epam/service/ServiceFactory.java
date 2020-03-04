package by.epam.service;

import by.epam.service.impl.*;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private static final InterviewService interviewService = new InterviewServiceImpl();
    private static final MessageService messageService = new MessageServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static final VacancyService vacancyService = new VacancyServiceImpl();
    private static final VacRespondedService vacRespondedService = new VacRespondedServiceImpl();
    private static final DialogService dialogService = new DialogServiceImpl();
    private static final ResumeService resumeService = new ResumeServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public VacRespondedService getVacRespondedService() {
        return vacRespondedService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public InterviewService getInterviewService() {
        return interviewService;
    }

    public UserService getUserService() {
        return userService;
    }

    public VacancyService getVacancyService() {
        return vacancyService;
    }

    public DialogService getDialogService() {
        return dialogService;
    }

    public ResumeService getResumeService() {
        return resumeService;
    }
}


