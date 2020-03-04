package by.epam.controller;


import by.epam.controller.impl.admin.AdminHomePage;
import by.epam.controller.impl.admin.DeleteUser;
import by.epam.controller.impl.general.*;
import by.epam.controller.impl.hr.*;
import by.epam.controller.impl.seeker.ResumePage;
import by.epam.controller.impl.seeker.SubmitResume;
import by.epam.controller.impl.seeker.SubscribeToVacancy;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> commandMap = new HashMap<>();

    CommandProvider() {
        commandMap.put(CommandName.LOGIN, new Login());
        commandMap.put(CommandName.LOGIN_PAGE, new LoginPage());
        commandMap.put(CommandName.REGISTRATION, new Registration());
        commandMap.put(CommandName.REGISTRATION_PAGE, new RegistrationPage());
        commandMap.put(CommandName.MAIN_PAGE, new MainPage());


        commandMap.put(CommandName.ALL_DIALOGS_PAGE, new AllDialogsPage());
        commandMap.put(CommandName.HOME_PAGE, new HomePage());
        commandMap.put(CommandName.ADMIN_HOME_PAGE, new AdminHomePage());
        commandMap.put(CommandName.LOGOUT, new Logout());
        commandMap.put(CommandName.PROFILE_PAGE, new ProfilePage());


        commandMap.put(CommandName.UPDATE_VACANCY_PAGE, new UpdateVacancyPage());
        commandMap.put(CommandName.UPDATE_VACANCY, new UpdateVacancy());
        commandMap.put(CommandName.DELETE_VACANCY, new DeleteVacancy());
        commandMap.put(CommandName.SUBSCRIBE_TO_VACANCY, new SubscribeToVacancy());
        commandMap.put(CommandName.ADD_VACANCY_PAGE, new AddVacancyPage());
        commandMap.put(CommandName.ADD_VACANCY, new AddVacancy());

        commandMap.put(CommandName.INTERVIEW_PAGE, new InterviewPage());
        commandMap.put(CommandName.SUBMIT_INTERVIEW, new SubmitInterview());

        commandMap.put(CommandName.DELETE_USER, new DeleteUser());

        commandMap.put(CommandName.DIALOG_PAGE, new DialogPage());
        commandMap.put(CommandName.ADD_DIALOG, new AddDialog());
        commandMap.put(CommandName.DELETE_DIALOG, new DeleteDialog());
        commandMap.put(CommandName.DIALOG, new Dialog());
        commandMap.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());
        commandMap.put(CommandName.SUBMIT_RESUME, new SubmitResume());
        commandMap.put(CommandName.RESUME_PAGE, new ResumePage());
        commandMap.put(CommandName.CHANGE_PASSWORD, new ChangePassword());
        // commandMap.put(CommandName.ERROR, new ErrorPage());


    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        return commandMap.get(commandName);
    }

}