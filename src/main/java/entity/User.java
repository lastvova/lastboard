package entity;

import enums.AccountStatus;
import enums.UserRole;

import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private String email;
    private String name;
    private UserRole userRole;
    private AccountStatus accountStatus;
    private List<Advertisement> advertisementList;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(String role) {
        if (role.equals("ADMIN")) {
            this.userRole = UserRole.ADMIN;
        } else if (role.equals("MODERATOR")) {
            this.userRole = UserRole.MODERATOR;
        } else this.userRole = UserRole.USER;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String status) {
        if (status.equals("BLOCKED")) {
            this.accountStatus = AccountStatus.BLOCKED;
        } else if (status.equals("ACTIVE")) {
            this.accountStatus = AccountStatus.ACTIVE;
        } else this.accountStatus = AccountStatus.NONACTIVE;
    }

    public List<Advertisement> getAdvertisementList() {
        return advertisementList;
    }

    public void setAdvertisementList(List<Advertisement> advertisementList) {
        this.advertisementList = advertisementList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword(), getEmail());
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userRole=" + userRole +
                ", accountStatus=" + accountStatus +
                ", advertisementsList=" + advertisementList +
                '}';
    }
}
