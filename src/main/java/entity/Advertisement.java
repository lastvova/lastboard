package entity;

import enums.AdvertisementStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Advertisement {
    private long id;
    private LocalDateTime date;
    private AdvertisementStatus advertisementStatus;
    private String tag;
    private String subject;
    private String body;
    private User user;

    public Advertisement() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AdvertisementStatus getAdvertisementStatus() {
        return advertisementStatus;
    }

    public void setAdvertisementStatus(String name) {
        if (name.equals("APPROVED")) {
            this.advertisementStatus = AdvertisementStatus.APPROVED;
        } else if (name.equals("INPROCESSING")) {
            this.advertisementStatus = AdvertisementStatus.INPROCESSING;
        } else if (name.equals("REJECTED")) {
            this.advertisementStatus = AdvertisementStatus.REJECTED;
        } else this.advertisementStatus = AdvertisementStatus.UNAPPROVED;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(getTag(), that.getTag()) &&
                Objects.equals(getSubject(), that.getSubject()) &&
                Objects.equals(getBody(), that.getBody()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTag(), getSubject(), getBody(), getUser().getName());
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", date=" + date +
                ", advertisementStatus=" + advertisementStatus +
                ", tag='" + tag + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
