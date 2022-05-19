package ch.bzz.produktliste.data;

import ch.bzz.produktliste.Helper;

import java.util.UUID;

public class User {
    private String userUUID = Helper.createUserUUID(this);
    private String username;
    private String role;
    private String passowrd;

    public User() {
    }

    public User(String username, String role, String passowrd) {
        this.username = username;
        this.role = role;
        this.passowrd = passowrd;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public void logon() {

    }

    public void logoff() {

    }

    @Override
    public String toString() {
        return "User: " + '\n' +
                "\t\t" + userUUID + '\n' +
                "\t\t" + username + '\n' +
                "\t\t" + role + '\n' +
                "\t\t" + passowrd + '\n';
    }
}
