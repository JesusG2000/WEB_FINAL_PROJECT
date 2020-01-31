package bean.dto;

import java.util.Objects;

public class VacResponded {
    private int id;
    private int userId;
    private int vacancyId;

    public VacResponded(int id, int userId, int vacancyId) {
        this.id = id;
        this.userId = userId;
        this.vacancyId = vacancyId;
    }

    public VacResponded(int userId, int vacancyId) {
        this.userId = userId;
        this.vacancyId = vacancyId;
    }

    public VacResponded() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacResponded that = (VacResponded) o;
        return id == that.id &&
                userId == that.userId &&
                vacancyId == that.vacancyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, vacancyId);
    }

    @Override
    public String toString() {
        return "VacResponded{" +
                "id=" + id +
                ", userId=" + userId +
                ", vacancyId=" + vacancyId +
                '}';
    }
}
