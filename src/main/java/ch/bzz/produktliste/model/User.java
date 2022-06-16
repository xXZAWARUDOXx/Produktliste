package ch.bzz.produktliste.model;

import ch.bzz.produktliste.Helper;

/**
 * a user, which uses the application
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 * */
public class User {
    private final String userUUID = Helper.createUUID();
    private String username;
    private String role;
    private String password;

    /*
     * default constructor
     * */
    public User() {
    }

    /*
     * constructor for instantiation
     *
     * */
    public User(String username,
                String role,
                String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    /*
     * gets userUUID
     * @return value of the userUUID
     * */
    public String getUserUUID() {
        return userUUID;
    }

    /*
     * gets username
     * @return value of the username
     * */
    public String getUsername() {
        return username;
    }

    /*
     * sets username
     * @param username
     * */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * gets role
     * @return value of the role
     * */
    public String getRole() {
        return role;
    }

    /*
     * sets role
     * @param role
     * */
    public void setRole(String role) {
        this.role = role;
    }

    /*
     * gets password
     * @return value of the password
     * */
    public String getPassword() {
        return password;
    }

    /*
     * sets password
     * @param password
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * loggs the user in
     * @return void
     * */
    public void logon() {

    }

    /*
     * loggs the user out
     * @return void
     * */
    public void logoff() {

    }

    /*
     * prints the user
     * @return user as string
     * */
    @Override
    public String toString() {
        return "User: " + '\n' +
                "\t\t" + userUUID + '\n' +
                "\t\t" + username + '\n' +
                "\t\t" + role + '\n' +
                "\t\t" + password;
    }
}
