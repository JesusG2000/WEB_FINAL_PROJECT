package controller;


import controller.impl.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class CommandProvider {
    private final Map<CommandName, Command> commandMap = new HashMap<>();
    private static final CommandProvider instance = new CommandProvider();

    CommandProvider() {
        commandMap.put(CommandName.LOGIN, new Login());
        //commandMap.put(CommandName.LOGIN_PAGE, new LoginPage());
        commandMap.put(CommandName.REGISTRATION, new Registration());
        commandMap.put(CommandName.REGISTRATION_PAGE, new RegistrationPage());
        commandMap.put(CommandName.MAIN_PAGE, new MainPage());


        commandMap.put(CommandName.ALL_DIALOGS_PAGE, new AllDialogsPage());//all_dialogs_page
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
        commandMap.put(CommandName.CHANGE_LANGUAGE , new ChangeLanguage());
       // commandMap.put(CommandName.ERROR, new ErrorPage());



    }

   public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        return commandMap.get(commandName);
    }
    public static CommandProvider getInstance(){
        return instance;
    }

}