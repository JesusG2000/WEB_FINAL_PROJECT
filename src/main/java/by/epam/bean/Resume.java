package by.epam.bean;

import java.util.Objects;

public class Resume {
    private int id;
    private int userId;
    private int vacancyId;
    private String userName;
    private String userSurname;
    private int userAge;
    private String userLanguage;
    private String userWorkExperience;
    private String userInfo;

    public Resume() {
    }

    public Resume(int id, int userId, int vacancyId, String userName, String userSurname, int userAge, String userLanguage, String userWorkExperience, String userInfo) {
        this.id = id;
        this.userId = userId;
        this.vacancyId = vacancyId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAge = userAge;
        this.userLanguage = userLanguage;
        this.userWorkExperience = userWorkExperience;
        this.userInfo = userInfo;
    }

    public Resume(int userId, int vacancyId, String userName, String userSurname, int userAge, String userLanguage, String userWorkExperience, String userInfo) {
        this.userId = userId;
        this.vacancyId = vacancyId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAge = userAge;
        this.userLanguage = userLanguage;
        this.userWorkExperience = userWorkExperience;
        this.userInfo = userInfo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public String getUserWorkExperience() {
        return userWorkExperience;
    }

    public void setUserWorkExperience(String userWorkExperience) {
        this.userWorkExperience = userWorkExperience;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return id == resume.id &&
                userId == resume.userId &&
                vacancyId == resume.vacancyId &&
                userAge == resume.userAge &&
                Objects.equals(userName, resume.userName) &&
                Objects.equals(userSurname, resume.userSurname) &&
                Objects.equals(userLanguage, resume.userLanguage) &&
                Objects.equals(userWorkExperience, resume.userWorkExperience) &&
                Objects.equals(userInfo, resume.userInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, vacancyId, userName, userSurname, userAge, userLanguage, userWorkExperience, userInfo);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", userId=" + userId +
                ", vacancyId=" + vacancyId +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userAge=" + userAge +
                ", userLanguage='" + userLanguage + '\'' +
                ", userWorkExperience='" + userWorkExperience + '\'' +
                ", userInfo='" + userInfo + '\'' +
                '}';
    }

}
