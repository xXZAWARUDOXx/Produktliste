package ch.bzz.produktliste.model;

import ch.bzz.produktliste.Helper;

/**
 * ein User, welcher die Applikation bedient
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 * */
public class User {
    private final String userUUID = Helper.createUserUUID(this);
    private String username;
    private String role;
    private String password;

    /*
     * default Konstruktor
     *
     * */
    public User() {
    }

    /*
     * Konstruktor zur Instanzierung
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
     * gettet userUUID
     *
     * @return Wert der userUUID
     * */
    public String getUserUUID() {
        return userUUID;
    }

    /*
     * gettet username
     *
     * @return Wert des usernamens
     * */
    public String getUsername() {
        return username;
    }

    /*
     * settet username
     *
     * @param username
     * */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * gettet role
     *
     * @return Wert der role
     * */
    public String getRole() {
        return role;
    }

    /*
     * settet role
     *
     * @param role
     * */
    public void setRole(String role) {
        this.role = role;
    }

    /*
     * gettet password
     *
     * @return Wert des passwords
     * */
    public String getPassword() {
        return password;
    }

    /*
     * settet password
     *
     * @param password
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * loggt den User ein
     *
     * @return void
     * */
    public void logon() {

    }

    /*
     * loggt den User aus
     *
     * @return void
     * */
    public void logoff() {

    }

    /*
     * formatiert die Ausgabe eines Users
     *
     * @return user als String
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
