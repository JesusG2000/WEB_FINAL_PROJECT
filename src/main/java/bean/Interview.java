package bean;


import java.util.Objects;
import java.sql.Date;

public class Interview {
    private int id;
    private int hrId;
    private int seekerId;
    private Date date;
    private String comment;

    public Interview(int id, int hrId, int seekerId, Date date, String comment) {
        this.id = id;
        this.hrId = hrId;
        this.seekerId = seekerId;
        this.date = date;
        this.comment = comment;
    }

    public Interview(int hrId, int seekerId, Date date, String comment) {
        this.hrId = hrId;
        this.seekerId = seekerId;
        this.date = date;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public int getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(int seekerId) {
        this.seekerId = seekerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date data) {
        this.date = data;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interview interview = (Interview) o;
        return id == interview.id &&
                hrId == interview.hrId &&
                seekerId == interview.seekerId &&
                Objects.equals(date, interview.date) &&
                Objects.equals(comment, interview.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hrId, seekerId, date, comment);
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", hrId=" + hrId +
                ", seekerId=" + seekerId +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }
}
